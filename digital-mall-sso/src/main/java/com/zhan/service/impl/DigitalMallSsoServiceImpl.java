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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class DigitalMallSsoServiceImpl implements DigitalMallSsoService {

    private static final String COOKIE_NAME = "DIGITAL_MALL_TOKEN";

    private static final String STATIC_URL = "http://localhost:10010/images";

    private static final String LOGIN_URL = "http://localhost:10010/console-login";

    private RedisTemplate redisTemplate;

    @Autowired
    public DigitalMallSsoServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void loginVerification(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        Cookie[] cookies = request.getCookies();
        String token = null;
        String userId = null;

        if(!request.getRequestURL().toString().contains(STATIC_URL) && !request.getRequestURL().toString().equals(LOGIN_URL)) {
            if (cookies != null && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    if (COOKIE_NAME.equals(cookie.getName())) {
                        token = cookie.getValue();
                        if (redisTemplate.opsForValue().get(token) != null) {
                            userId = redisTemplate.opsForValue().get(token) + "";
                        }
                    }
                }
            }

            if (userId != null) {
                Cookie cookie = new Cookie(COOKIE_NAME, token);
                cookie.setMaxAge(5 * 60);
                response.addCookie(cookie);
                redisTemplate.opsForValue().set(token, userId, 5, TimeUnit.MINUTES);
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                servletRequest.getRequestDispatcher("/toLoginPage").forward(servletRequest, servletResponse);
            }
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
