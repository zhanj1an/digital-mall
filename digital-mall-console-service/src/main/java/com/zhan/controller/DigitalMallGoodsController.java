package com.zhan.controller;

import com.zhan.model.DigitalMallGoods;
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

    @RequestMapping("/getGoodsList")
    public List<DigitalMallGoods> getGoodsList(){
        return digitalMallGoodsService.getGoodsList();
    }

    @RequestMapping("/addGoods")
    public void addGoods(DigitalMallGoods digitalMallGoods){
        digitalMallGoodsService.addGoods(digitalMallGoods);
    }

    @RequestMapping("/deleteGoods")
    public void deleteGoods(int id){
        digitalMallGoodsService.deleteGoods(id);
    }

    @RequestMapping("/getGoodsById")
    public DigitalMallGoods getGoodsById(int id){
        return digitalMallGoodsService.getGoodsById(id);
    }

    @RequestMapping("/updateGoods")
    public void updateGoods(DigitalMallGoods goods){
        digitalMallGoodsService.updateGoods(goods);
    }
}
