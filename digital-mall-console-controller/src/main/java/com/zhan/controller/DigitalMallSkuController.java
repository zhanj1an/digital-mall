package com.zhan.controller;

import com.zhan.model.DigitalMallAttribute;
import com.zhan.model.DigitalMallAttributeValue;
import com.zhan.model.DigitalMallSku;
import com.zhan.service.DigitalMallAttributeService;
import com.zhan.service.DigitalMallAttributeValueService;
import com.zhan.service.DigitalMallSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;

@Controller
public class DigitalMallSkuController {

    private DigitalMallSkuService digitalMallSkuService;

    private DigitalMallAttributeService digitalMallAttributeService;

    private DigitalMallAttributeValueService digitalMallAttributeValueService;

    @Autowired
    public DigitalMallSkuController(DigitalMallSkuService digitalMallSkuService,
                                    DigitalMallAttributeService digitalMallAttributeService,
                                    DigitalMallAttributeValueService digitalMallAttributeValueService) {
        this.digitalMallSkuService = digitalMallSkuService;
        this.digitalMallAttributeService = digitalMallAttributeService;
        this.digitalMallAttributeValueService = digitalMallAttributeValueService;
    }

    @RequestMapping("/addSku")
    public String addSku(String attrName, String [] attrValue, String[] price, String[] stock, String id, String name){

        String updateTime = System.currentTimeMillis() + "";

        int goodsId = Integer.parseInt(id);

        HashSet<DigitalMallAttributeValue> set = new HashSet<>();

        //遍历拼接sku信息
        for (int i = 0; i < attrValue.length; i++) {
            DigitalMallSku sku = new DigitalMallSku(goodsId, name, attrValue[i], Double.parseDouble(price[i]),
                    Integer.parseInt(stock[i]), 0, updateTime);
            digitalMallSkuService.insert(sku);
        }

        String[] attrNames = attrName.split(",");
        //遍历生成商品属性信息
        for (int i = 0; i < attrNames.length; i++) {
            DigitalMallAttribute attr = new DigitalMallAttribute(attrNames[i], goodsId, updateTime);
            int attrId = digitalMallAttributeService.insert(attr);

            //属性值添加
            for (String value : attrValue) {
                String[] attrValues = value.split(",");
                DigitalMallAttributeValue attributeValue = new DigitalMallAttributeValue(attrValues[i], attrId, goodsId, updateTime);
                if(set.add(attributeValue)){
                    digitalMallAttributeValueService.insert(attributeValue);
                }
            }
        }
        return "redirect:getGoodsList";
    }

    @RequestMapping("/getSkuList")
    public String getSkuList(Model model, DigitalMallSku sku){
        model.addAttribute("pageInfo", digitalMallSkuService.selectSkuByCriteria(sku));
        model.addAttribute("isDelete", sku.getIsDelete());
        model.addAttribute("goodsName", sku.getGoodsName());
        return "sku-list";
    }

    @RequestMapping("/toUpdateView")
    public String toUpdateView(String id, Model model){
        model.addAttribute("sku", digitalMallSkuService.selectSkuById(Integer.parseInt(id)));
        return "sku-edit";
    }

    @RequestMapping("/updateSku")
    public String updateSku(String id, String stock){
        digitalMallSkuService.updateSku(Integer.parseInt(id), Integer.parseInt(stock));
        return "redirect:getSkuList";
    }

    @ResponseBody
    @RequestMapping("/deleteSku")
    public int deleteSku(String id){
        return digitalMallSkuService.deleteSku(Integer.parseInt(id));
    }
}
