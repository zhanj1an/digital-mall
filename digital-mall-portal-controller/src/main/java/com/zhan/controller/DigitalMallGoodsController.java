package com.zhan.controller;

import com.alibaba.fastjson.JSON;
import com.zhan.model.*;
import com.zhan.service.DigitalMallBrandService;
import com.zhan.service.DigitalMallCartService;
import com.zhan.service.DigitalMallCategoryService;
import com.zhan.service.DigitalMallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller
public class DigitalMallGoodsController {

    private DigitalMallGoodsService digitalMallGoodsService;

    private DigitalMallBrandService digitalMallBrandService;

    private DigitalMallCategoryService digitalMallCategoryService;

    private DigitalMallCartService digitalMallCartService;

    @Autowired
    public DigitalMallGoodsController(DigitalMallGoodsService digitalMallGoodsService,
                                      DigitalMallBrandService digitalMallBrandService,
                                      DigitalMallCategoryService digitalMallCategoryService,
                                      DigitalMallCartService digitalMallCartService) {
        this.digitalMallGoodsService = digitalMallGoodsService;
        this.digitalMallBrandService = digitalMallBrandService;
        this.digitalMallCategoryService = digitalMallCategoryService;
        this.digitalMallCartService = digitalMallCartService;
    }

    @RequestMapping("/index")
    public String index(Model model, ServletRequest servletRequest){
        model.addAttribute("cartInfo", digitalMallCartService.getCartInfo(servletRequest));
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
    public void addToCart(String number, String skuId, ServletRequest servletRequest, ServletResponse servletResponse){
        digitalMallCartService.addToCart(number, skuId, servletRequest, servletResponse);
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

}
