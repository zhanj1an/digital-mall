package com.zhan.service;

import com.zhan.dao.DigitalMallGoodsMapper;
import com.zhan.dao.DigitalMallSkuMapper;
import com.zhan.model.DigitalMallCartGoods;
import com.zhan.model.DigitalMallCartInfo;
import com.zhan.model.DigitalMallGoods;
import com.zhan.model.DigitalMallSku;
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

    private DigitalMallSkuMapper digitalMallSkuMapper;

    private DigitalMallGoodsMapper digitalMallGoodsMapper;

    private static final String COOKIE_PRICE_NAME = "totalPrice";

    private static final String COOKIE_NUM_NAME = "totalNum";

    //cookie 商品key前缀
    private static final String COOKIE_KEY_PRE = "cartGoodsId_";

    @Autowired
    public DigitalMallCartService(DigitalMallSkuMapper digitalMallSkuMapper,
                                  DigitalMallGoodsMapper digitalMallGoodsMapper) {
        this.digitalMallSkuMapper = digitalMallSkuMapper;
        this.digitalMallGoodsMapper = digitalMallGoodsMapper;
    }

    public void addToCart(String number, String skuId, ServletRequest servletRequest, ServletResponse servletResponse){

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        Double goodsPrice = digitalMallSkuMapper.selectByPrimaryKey(Integer.parseInt(skuId)).getPrice();

        int goodsNum = 0;
        double totalPrice = 0.0;
        int totalNum = 0;

        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(skuId)) {
                    goodsNum = Integer.parseInt(cookie.getValue());
                }
                if (cookie.getName().equals(COOKIE_PRICE_NAME)) {
                    totalPrice = Double.parseDouble(cookie.getValue());
                }
                if(cookie.getName().equals(COOKIE_NUM_NAME)){
                    totalNum = Integer.parseInt(cookie.getValue());
                }
            }
        }

        totalPrice = totalPrice + (goodsPrice * Integer.parseInt(number));
        response.addCookie(new Cookie(COOKIE_KEY_PRE + skuId,goodsNum + Integer.parseInt(number) + ""));
        response.addCookie(new Cookie(COOKIE_PRICE_NAME, totalPrice + ""));
        response.addCookie(new Cookie(COOKIE_NUM_NAME, totalNum + Integer.parseInt(number) + ""));
    }

    /**
     * 获得存在cookie中的商品信息
     * @param servletRequest request
     * @return DigitalMallCartInfo
     */
    public DigitalMallCartInfo getCartInfo(ServletRequest servletRequest){

        DigitalMallCartInfo cartInfo = new DigitalMallCartInfo(null, 0, 0);

        List<DigitalMallCartGoods> goodsList = new ArrayList<>();

        HttpServletRequest request = (HttpServletRequest)servletRequest;

        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().contains(COOKIE_KEY_PRE)){
                    DigitalMallSku sku = digitalMallSkuMapper.selectByPrimaryKey(Integer.parseInt(cookie.getName().
                            split("_")[1]));
                    DigitalMallGoods goods = digitalMallGoodsMapper.selectByPrimaryKey(sku.getGoodsId());
                    goodsList.add(DigitalMallCartGoods.builder().
                            goodsName(goods.getName()).goodsAttr(sku.getAttribute()).
                            imgUrl(goods.getImageUrl().split(",")[0]).
                            goodsNum(Integer.parseInt(cookie.getValue())).
                            price(sku.getPrice()).build());
                }
                if(cookie.getName().equals(COOKIE_NUM_NAME)){
                    cartInfo.setTotalNum(Integer.parseInt(cookie.getValue()));
                }
                if(cookie.getName().equals(COOKIE_PRICE_NAME)){
                    cartInfo.setTotalPrice(Double.parseDouble(cookie.getValue()));
                }
            }
            cartInfo.setGoodsList(goodsList);
        }
        return cartInfo;
    }
}
