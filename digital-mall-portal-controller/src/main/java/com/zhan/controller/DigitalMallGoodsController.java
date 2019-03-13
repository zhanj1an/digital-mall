package com.zhan.controller;

import com.zhan.model.DigitalMallGoodsInfo;
import com.zhan.model.DigitalMallGoodsSynopsis;
import com.zhan.model.DigitalMallSku;
import com.zhan.service.DigitalMallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


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
    public Map<String, String> queryStock(String goodsId, String attr){
        DigitalMallSku sku = digitalMallGoodsService.queryGoodsStock(Integer.parseInt(goodsId), attr);
        Map<String, String> map = new HashMap<>();
        if(sku != null) {
            map.put("price", sku.getPrice() + "");
            map.put("stock", sku.getStock() + "");
            map.put("skuId", sku.getId() + "");
        }else{
            map.put("price", 0 + "");
            map.put("stock", 0 + "");
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
