package com.zhan.controller;

import com.zhan.service.impl.DigitalMallUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DigitalMallSsoController {

    private DigitalMallUserServiceImpl digitalMallUserService;

    @Autowired
    public DigitalMallSsoController(DigitalMallUserServiceImpl digitalMallUserService) {
        this.digitalMallUserService = digitalMallUserService;
    }

    @RequestMapping("toLoginPage")
    public String toLoginPage(){
        return "console-login";
    }

    @ResponseBody
    @RequestMapping("/console-login")
    public int login(String username, String password, HttpServletResponse response, HttpServletRequest request){
        return digitalMallUserService.consoleLogin(username, password, response, request);
    }
}
