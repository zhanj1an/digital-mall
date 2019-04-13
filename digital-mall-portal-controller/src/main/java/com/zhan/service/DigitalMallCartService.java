package com.zhan.service;

import com.zhan.dao.DigitalMallSkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class DigitalMallCartService {

    private DigitalMallSkuMapper digitalMallSkuMapper;

    private static final String COOKIE_PRICE_NAME = "totalPrice";

    @Autowired
    public DigitalMallCartService(DigitalMallSkuMapper digitalMallSkuMapper) {
        this.digitalMallSkuMapper = digitalMallSkuMapper;
    }

    public void addToCart(String number, String skuId, ServletRequest servletRequest, ServletResponse servletResponse){

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        Double goodsPrice = digitalMallSkuMapper.selectByPrimaryKey(Integer.parseInt(skuId)).getPrice();

        int goodsNum = 0;
        double totalPrice = 0.0;

        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(skuId)) {
                    goodsNum = Integer.parseInt(cookie.getValue());
                }
                if (cookie.getName().equals(COOKIE_PRICE_NAME)) {
                    totalPrice = Double.parseDouble(cookie.getValue());
                }
            }
        }

        totalPrice = totalPrice + (goodsPrice * Integer.parseInt(number));
        response.addCookie(new Cookie(skuId,goodsNum + Integer.parseInt(number) + ""));
        response.addCookie(new Cookie(COOKIE_PRICE_NAME, totalPrice + ""));

    }
}
