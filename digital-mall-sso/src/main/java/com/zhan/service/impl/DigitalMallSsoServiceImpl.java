package com.zhan.service.impl;

import com.zhan.service.DigitalMallSsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class DigitalMallSsoServiceImpl implements DigitalMallSsoService {

    private static final String COOKIE_NAME = "DIGITAL_MALL_TOKEN";

    private RedisTemplate redisTemplate;

    @Autowired
    public DigitalMallSsoServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void loginVerification(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;

        Cookie[] cookies = request.getCookies();
        String token;
        String userName = null;

        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies) {
                if(COOKIE_NAME.equals(cookie.getName())){
                    token = cookie.getValue();

                    if(redisTemplate.opsForValue().get(token) != null){
                        userName = redisTemplate.opsForValue().get(token).toString();
                    }
                }
            }
        }

        if(userName != null){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            servletRequest.getRequestDispatcher("/toLoginPage").forward(servletRequest, servletResponse);
        }
    }
}
