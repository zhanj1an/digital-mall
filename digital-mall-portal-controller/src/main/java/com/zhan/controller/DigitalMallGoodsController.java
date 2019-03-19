package com.zhan.controller;

import com.alibaba.fastjson.JSON;
import com.zhan.model.*;
import com.zhan.service.DigitalMallBrandService;
import com.zhan.service.DigitalMallCategoryService;
import com.zhan.service.DigitalMallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
public class DigitalMallGoodsController {

    private DigitalMallGoodsService digitalMallGoodsService;

    private DigitalMallBrandService digitalMallBrandService;

    private DigitalMallCategoryService digitalMallCategoryService;

    @Autowired
    public DigitalMallGoodsController(DigitalMallGoodsService digitalMallGoodsService,
                                      DigitalMallBrandService digitalMallBrandService,
                                      DigitalMallCategoryService digitalMallCategoryService) {
        this.digitalMallGoodsService = digitalMallGoodsService;
        this.digitalMallBrandService = digitalMallBrandService;
        this.digitalMallCategoryService = digitalMallCategoryService;
    }

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("goodsSynopsisList",digitalMallGoodsService.getRollGoodsSynopsis());
        model.addAttribute("brandList", digitalMallBrandService.getBrandList());
        model.addAttribute("categoryList", digitalMallCategoryService.getCategoryList());
        return "index";
    }

    @RequestMapping("/searchGoods")
    public String searchGoods(Model model, DigitalMallGoods goods){
        model.addAttribute("page", digitalMallGoodsService.selectGoodsByCriteria(goods));
        model.addAttribute("name", goods.getName());
        model.addAttribute("brandId", goods.getBrandId());
        model.addAttribute("categoryId", goods.getCategoryId());
        model.addAttribute("brandList", digitalMallBrandService.getBrandList());
        model.addAttribute("categoryList", digitalMallCategoryService.getCategoryList());
        return "store";
    }

    @RequestMapping("/toGoodsView")
    public String toGoodsView(DigitalMallGoodsSynopsis goodsSynopsis, Model model){
        model.addAttribute("goodsInfo", new DigitalMallGoodsInfo(goodsSynopsis,
                digitalMallGoodsService.getGoodsAttribute(goodsSynopsis.getGoodsId())));
        return "product";
    }

    @ResponseBody
    @RequestMapping("/queryStock")
    public Map<String, String> queryStock(String goodsId, String attr){
        DigitalMallSku sku = digitalMallGoodsService.queryGoodsStock(Integer.parseInt(goodsId), attr);
        Map<String, String> map = new HashMap<>();
        if(sku != null) {
            map.put("price", sku.getPrice() + "");
            map.put("stock", "库存:" + sku.getStock());
            map.put("skuId", sku.getId() + "");
        }else{
            map.put("price", "商品已下架");
            map.put("stock", "");
            map.put("skuId", 0 + "");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/addToCart")
    public int addToCart(String number, String skuId){
        System.out.println(number);
        System.out.println(skuId);
        return 1;
    }


}
