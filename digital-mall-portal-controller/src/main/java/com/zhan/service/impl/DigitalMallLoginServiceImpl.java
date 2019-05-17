package com.zhan.service.impl;

import com.zhan.dao.DigitalMallCartGoodsInfoMapper;
import com.zhan.dao.DigitalMallCartMapper;
import com.zhan.dao.DigitalMallUserMapper;
import com.zhan.model.*;
import com.zhan.service.DigitalMallCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class DigitalMallLoginServiceImpl {

    private static final String PORTAL_COOKIE_NAME = "PORTAL_TOKEN";

    private RedisTemplate redisTemplate;

    private DigitalMallUserMapper digitalMallUserMapper;

    @Autowired
    @Lazy
    private DigitalMallCartService digitalMallCartService;

    private DigitalMallCartMapper digitalMallCartMapper;

    private DigitalMallCartGoodsInfoMapper digitalMallCartGoodsInfoMapper;

    @Autowired
    public DigitalMallLoginServiceImpl(RedisTemplate redisTemplate,
                                       DigitalMallUserMapper digitalMallUserMapper,
                                       DigitalMallCartMapper digitalMallCartMapper,
                                       DigitalMallCartGoodsInfoMapper digitalMallCartGoodsInfoMapper) {
        this.redisTemplate = redisTemplate;
        this.digitalMallUserMapper = digitalMallUserMapper;
        this.digitalMallCartMapper = digitalMallCartMapper;
        this.digitalMallCartGoodsInfoMapper = digitalMallCartGoodsInfoMapper;
    }

    public int getLoginStatus(ServletRequest servletRequest){
        HttpServletRequest request = (HttpServletRequest)servletRequest;

        Cookie[] cookies = request.getCookies();

        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(PORTAL_COOKIE_NAME) && redisTemplate.opsForValue().get(cookie.getValue()) != null){
                    return (int)redisTemplate.opsForValue().get(cookie.getValue());
                }
            }
        }
        return 0;
    }

    public String login(ServletRequest servletRequest, ServletResponse servletResponse, String username, String password){
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        DigitalMallUser user = digitalMallUserMapper.selectGeneralUser(DigitalMallUser.builder().username(username).password(password).build());

        if(user == null){
            return "login";
        }

        String uuid = UUID.randomUUID().toString();
        Cookie cookie = new Cookie(PORTAL_COOKIE_NAME, uuid);
        cookie.setMaxAge(10 * 60);
        response.addCookie(cookie);
        redisTemplate.opsForValue().set(uuid, user.getId(), 10, TimeUnit.MINUTES);

        //Cookie与数据库中的购物车信息整合
        int cartId;
        DigitalMallCartInfo cartInfo = digitalMallCartService.getCartInfo(servletRequest);
        DigitalMallCart cart = digitalMallCartMapper.selectByUserId(user.getId());
        if(cart == null) {
            DigitalMallCart digitalMallCart = new DigitalMallCart(user.getId(), cartInfo.getTotalNum(), cartInfo.getTotalPrice());
            digitalMallCartMapper.insert(digitalMallCart);
            cartId = digitalMallCart.getId();
        }else{
            cartId = cart.getId();
            cart.setTotalNum(cart.getTotalNum() + cartInfo.getTotalNum());
            cart.setTotalPrice(cart.getTotalPrice() + cartInfo.getTotalPrice());
            digitalMallCartMapper.updateByPrimaryKey(cart);
        }

        //整合购物车商品信息
        if(cartInfo.getGoodsList() != null) {
            for (DigitalMallCartGoods goodsInfo : cartInfo.getGoodsList()) {
                DigitalMallCartGoodsInfo cartGoodsInfo = digitalMallCartGoodsInfoMapper.selectBySkuId(goodsInfo.getSkuId());
                if (cartGoodsInfo == null) {
                    digitalMallCartGoodsInfoMapper.insert(new DigitalMallCartGoodsInfo(goodsInfo.getSkuId(),
                            cartId, goodsInfo.getGoodsName(), goodsInfo.getGoodsAttr(), goodsInfo.getImgUrl(),
                            goodsInfo.getGoodsNum(), goodsInfo.getPrice()));
                } else {
                    cartGoodsInfo.setGoodsNum(cartGoodsInfo.getGoodsNum() + goodsInfo.getGoodsNum());
                    digitalMallCartGoodsInfoMapper.updateByPrimaryKey(cartGoodsInfo);
                }
            }
        }
        digitalMallCartService.deleteCart(servletRequest, servletResponse);

        return "200";
    }
}
