package com.zhan.controller;

import com.zhan.model.DigitalMallGoodsInfo;
import com.zhan.model.DigitalMallGoodsSynopsis;
import com.zhan.service.DigitalMallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class DigitalMallGoodsController {

    private DigitalMallGoodsService digitalMallGoodsService;

    @Autowired
    public DigitalMallGoodsController(DigitalMallGoodsService digitalMallGoodsService) {
        this.digitalMallGoodsService = digitalMallGoodsService;
    }

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("goodsSynopsisList",digitalMallGoodsService.getRollGoodsSynopsis());
        return "index";
    }

    @RequestMapping("/toGoodsView")
    public String toGoodsView(DigitalMallGoodsSynopsis goodsSynopsis, Model model){
        model.addAttribute("goodsInfo", new DigitalMallGoodsInfo(goodsSynopsis,
                digitalMallGoodsService.getGoodsAttribute(goodsSynopsis.getGoodsId())));
        return "product";
    }

    @ResponseBody
    @RequestMapping("/queryStock")
    public int queryStock(String goodsId, String attr){
        System.out.println(goodsId);
        System.out.println(attr);
        return 1;
    }
}
