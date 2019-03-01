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
    public Integer deleteGoods(int id){
        return digitalMallGoodsService.deleteGoods(id);
    }

    @RequestMapping("/getGoodsById")
    public DigitalMallGoods getGoodsById(int id){
        return digitalMallGoodsService.getGoodsById(id);
    }

    @RequestMapping("/updateGoods")
    public void updateGoods(DigitalMallGoods goods){
        digitalMallGoodsService.updateGoods(goods);
    }

    @RequestMapping("/showGoods")
    public Integer showGoods(int id){
        return digitalMallGoodsService.showGoods(id);
    }

    @RequestMapping("/hideGoods")
    public Integer hideGoods(int id){
        return digitalMallGoodsService.hideGoods(id);
    }

    @RequestMapping("selectGoodsByCriteria")
    public List<DigitalMallGoods> selectGoodsByCriteria(DigitalMallGoods goods){
        return digitalMallGoodsService.selectGoodsByCriteria(goods);
    }
}
