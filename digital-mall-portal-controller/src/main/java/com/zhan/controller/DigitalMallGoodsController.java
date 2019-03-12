package com.zhan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DigitalMallGoodsController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/toGoodsView")
    public String toGoodsView(String id){
        System.out.println(id);
        return "product";
    }
}
