package com.zhan.service;

import com.zhan.dao.DigitalMallCartGoodsInfoMapper;
import com.zhan.dao.DigitalMallCartMapper;
import com.zhan.dao.DigitalMallGoodsMapper;
import com.zhan.dao.DigitalMallSkuMapper;
import com.zhan.model.*;
import com.zhan.service.impl.DigitalMallLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class DigitalMallCartService {

    private static final String COOKIE_PRICE_NAME = "totalPrice";

    private static final String COOKIE_NUM_NAME = "totalNum";

    //cookie 商品key前缀
    private static final String COOKIE_KEY_PRE = "cartGoodsId_";

    private DigitalMallSkuMapper digitalMallSkuMapper;

    private DigitalMallGoodsMapper digitalMallGoodsMapper;

    private DigitalMallLoginServiceImpl digitalMallLoginService;

    private DigitalMallCartMapper digitalMallCartMapper;

    private DigitalMallCartGoodsInfoMapper digitalMallCartGoodsInfoMapper;

    @Autowired
    public DigitalMallCartService(DigitalMallSkuMapper digitalMallSkuMapper,
                                  DigitalMallGoodsMapper digitalMallGoodsMapper,
                                  DigitalMallLoginServiceImpl digitalMallLoginService,
                                  DigitalMallCartMapper digitalMallCartMapper,
                                  DigitalMallCartGoodsInfoMapper digitalMallCartGoodsInfoMapper) {
        this.digitalMallSkuMapper = digitalMallSkuMapper;
        this.digitalMallGoodsMapper = digitalMallGoodsMapper;
        this.digitalMallLoginService = digitalMallLoginService;
        this.digitalMallCartMapper = digitalMallCartMapper;
        this.digitalMallCartGoodsInfoMapper = digitalMallCartGoodsInfoMapper;
    }

    public void addToCart(String number, String skuId, ServletRequest servletRequest, ServletResponse servletResponse){

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        Double goodsPrice = digitalMallSkuMapper.selectByPrimaryKey(Integer.parseInt(skuId)).getPrice();

        int userId = digitalMallLoginService.getLoginStatus(servletRequest);
        //购物车信息存入Cookie
        if(userId == 0) {
            int goodsNum = 0;
            double totalPrice = 0.0;
            int totalNum = 0;

            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(skuId)) {
                        goodsNum = Integer.parseInt(cookie.getValue());
                    }
                    if (cookie.getName().equals(COOKIE_PRICE_NAME)) {
                        totalPrice = Double.parseDouble(cookie.getValue());
                    }
                    if (cookie.getName().equals(COOKIE_NUM_NAME)) {
                        totalNum = Integer.parseInt(cookie.getValue());
                    }
                }
            }

            totalPrice = totalPrice + (goodsPrice * Integer.parseInt(number));
            response.addCookie(new Cookie(COOKIE_KEY_PRE + skuId, goodsNum + Integer.parseInt(number) + ""));
            response.addCookie(new Cookie(COOKIE_PRICE_NAME, totalPrice + ""));
            response.addCookie(new Cookie(COOKIE_NUM_NAME, totalNum + Integer.parseInt(number) + ""));
        }else{
            DigitalMallCart cart = digitalMallCartMapper.selectByUserId(userId);
            if(cart != null) {
                cart.setTotalNum(cart.getTotalNum() + Integer.parseInt(number));
                cart.setTotalPrice(cart.getTotalPrice() + (goodsPrice * Integer.parseInt(number)));
                digitalMallCartMapper.updateByPrimaryKey(cart);
            }else{
                cart = new DigitalMallCart(userId, Integer.parseInt(number), goodsPrice * Integer.parseInt(number));
                digitalMallCartMapper.insert(cart);
            }

            DigitalMallCartGoodsInfo goodsInfo = digitalMallCartGoodsInfoMapper.selectBySkuId(Integer.parseInt(skuId));
            if(goodsInfo == null){
                DigitalMallSku sku = digitalMallSkuMapper.selectByPrimaryKey(Integer.parseInt(skuId));
                DigitalMallGoods goods = digitalMallGoodsMapper.selectByPrimaryKey(sku.getGoodsId());
                digitalMallCartGoodsInfoMapper.insert(new DigitalMallCartGoodsInfo(sku.getId(),
                        cart.getId(), goods.getName(), sku.getAttribute(), goods.getImageUrl().split(",")[0],
                        Integer.parseInt(number), goodsPrice));
            }else{
                goodsInfo.setGoodsNum(goodsInfo.getGoodsNum() + Integer.parseInt(number));
                digitalMallCartGoodsInfoMapper.updateByPrimaryKey(goodsInfo);
            }
        }
    }

    /**
     * 获得存在cookie中的商品信息
     * @param servletRequest request
     * @return DigitalMallCartInfo
     */
    public DigitalMallCartInfo getCartInfo(ServletRequest servletRequest){

        DigitalMallCartInfo cartInfo = new DigitalMallCartInfo(null, 0, 0);

        List<DigitalMallCartGoods> goodsList = new ArrayList<>();

        int userId = digitalMallLoginService.getLoginStatus(servletRequest);

        //用户没有登录，从Cookie中读取购物车信息
        if(userId == 0) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().contains(COOKIE_KEY_PRE)) {
                        DigitalMallSku sku = digitalMallSkuMapper.selectByPrimaryKey(Integer.parseInt(cookie.getName().
                                split("_")[1]));
                        DigitalMallGoods goods = digitalMallGoodsMapper.selectByPrimaryKey(sku.getGoodsId());
                        goodsList.add(DigitalMallCartGoods.builder().skuId(sku.getId()).
                                goodsName(goods.getName()).goodsAttr(sku.getAttribute()).
                                imgUrl(goods.getImageUrl().split(",")[0]).
                                goodsNum(Integer.parseInt(cookie.getValue())).
                                price(sku.getPrice()).build());
                    }
                    if (cookie.getName().equals(COOKIE_NUM_NAME)) {
                        cartInfo.setTotalNum(Integer.parseInt(cookie.getValue()));
                    }
                    if (cookie.getName().equals(COOKIE_PRICE_NAME)) {
                        cartInfo.setTotalPrice(Double.parseDouble(cookie.getValue()));
                    }
                }
                cartInfo.setGoodsList(goodsList);
            }
        }
        //从数据库中读取购物车信息
        else{
            DigitalMallCart digitalMallCart = digitalMallCartMapper.selectByUserId(userId);
            if(digitalMallCart != null) {
                List<DigitalMallCartGoodsInfo> cartGoodsInfoList = digitalMallCartGoodsInfoMapper.selectByCartId(digitalMallCart.getId());
                for (DigitalMallCartGoodsInfo goodsInfo : cartGoodsInfoList) {
                    goodsList.add(DigitalMallCartGoods.builder().skuId(goodsInfo.getSkuId()).
                            goodsName(goodsInfo.getGoodsName()).goodsAttr(goodsInfo.getGoodsAttr()).
                            imgUrl(goodsInfo.getImgUrl()).goodsNum(goodsInfo.getGoodsNum()).
                            price(goodsInfo.getGoodsPrice()).build());
                }
                cartInfo.setTotalNum(digitalMallCart.getTotalNum());
                cartInfo.setTotalPrice(digitalMallCart.getTotalPrice());
                cartInfo.setGoodsList(goodsList);
            }
        }
        return cartInfo;
    }

    public void deleteCart(ServletRequest servletRequest, ServletResponse servletResponse){

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        int userId = digitalMallLoginService.getLoginStatus(servletRequest);

        if(userId == 0) {
            Cookie[] cookies = request.getCookies();

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().contains(COOKIE_KEY_PRE)) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                    if (cookie.getName().equals(COOKIE_NUM_NAME)) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                    if (cookie.getName().equals(COOKIE_PRICE_NAME)) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
        }else{
            DigitalMallCart cart = digitalMallCartMapper.selectByUserId(userId);
            if(cart != null){
                digitalMallCartMapper.deleteByUserId(userId);
                digitalMallCartGoodsInfoMapper.deleteByCartId(cart.getId());
            }
        }
    }
}
