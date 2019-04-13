package com.zhan.service.impl;

import com.zhan.dao.DigitalMallUserMapper;
import com.zhan.model.DigitalMallUser;
import com.zhan.service.DigitalMallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class DigitalMallUserServiceImpl implements DigitalMallUserService {

    private static final String CONTROLLER_COOKIE_NAME = "CONTROLLER_TOKEN";

//    private static final String PORTAL_COOKIE_NAME = "PORTAL_TOKEN";

    private DigitalMallUserMapper digitalMallUserMapper;

    private RedisTemplate redisTemplate;

    @Autowired
    public DigitalMallUserServiceImpl(DigitalMallUserMapper digitalMallUserMapper,
                                      RedisTemplate redisTemplate) {
        this.digitalMallUserMapper = digitalMallUserMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public int consoleLogin(String username, String password, HttpServletResponse response, HttpServletRequest request) {
        DigitalMallUser user = digitalMallUserMapper.selectAdminUser(DigitalMallUser.builder().username(username).password(password).build());
        if(user == null){
            return 0;
        }
        String uuid = UUID.randomUUID().toString();
        Cookie cookie = new Cookie(CONTROLLER_COOKIE_NAME, uuid);
        cookie.setMaxAge(5 * 60);
        response.addCookie(cookie);
        redisTemplate.opsForValue().set(uuid, user.getId(), 5, TimeUnit.MINUTES);
        return 200;
    }

    @Override
    public int portalLogin(String username, String password, HttpServletResponse response, HttpServletRequest request) {
        DigitalMallUser user = digitalMallUserMapper.selectGeneralUser(DigitalMallUser.builder().username(username).password(password).build());
        if(user == null){
            return 0;
        }
        return 1;
    }
}
