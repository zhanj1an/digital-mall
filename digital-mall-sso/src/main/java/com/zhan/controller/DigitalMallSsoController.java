package com.zhan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DigitalMallSsoController {

    @RequestMapping("toLoginPage")
    public String toLoginPage(){
        return "login";
    }
}
