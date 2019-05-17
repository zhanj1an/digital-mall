package com.zhan.controller;

import com.zhan.service.DigitalMallOrderGoodsService;
import com.zhan.service.DigitalMallOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DigitalMallOrderController {

    private DigitalMallOrderService digitalMallOrderService;

    private DigitalMallOrderGoodsService digitalMallOrderGoodsService;

    @Autowired
    public DigitalMallOrderController(DigitalMallOrderService digitalMallOrderService,
                                      DigitalMallOrderGoodsService digitalMallOrderGoodsService) {
        this.digitalMallOrderService = digitalMallOrderService;
        this.digitalMallOrderGoodsService = digitalMallOrderGoodsService;
    }

    @RequestMapping("getOrderList")
    public String getOrderList(Model model){
        model.addAttribute("orderList", digitalMallOrderService.getOrderList());
        return "order-list";
    }

    @RequestMapping("toOrderInfoView")
    public String toOrderInfoView(String id, Model model){
        model.addAttribute("goodsList", digitalMallOrderGoodsService.getOrderGoodList(id));
        return "order-goods";
    }
}
