package com.zhan.controller;

import com.zhan.model.DigitalMallGoodsAttribute;
import com.zhan.model.DigitalMallGoodsSynopsis;
import com.zhan.service.DigitalMallGoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DigitalMallGoodsController {

    private DigitalMallGoodsServiceImpl digitalMallGoodsService;

    @Autowired
    public DigitalMallGoodsController(DigitalMallGoodsServiceImpl digitalMallGoodsService) {
        this.digitalMallGoodsService = digitalMallGoodsService;
    }

    @RequestMapping("/getRollGoodsSynopsis")
    public List<DigitalMallGoodsSynopsis> getRollGoodsSynopsis(){
        return digitalMallGoodsService.getRollGoodsSynopsis();
    }

    @RequestMapping("/getGoodsAttribute")
    public List<DigitalMallGoodsAttribute> getGoodsAttribute(int goodsId){
        return digitalMallGoodsService.getGoodsAttribute(goodsId);
    }
}
