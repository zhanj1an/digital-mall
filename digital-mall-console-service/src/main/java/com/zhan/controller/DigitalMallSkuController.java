package com.zhan.controller;

import com.zhan.model.DigitalMallSku;
import com.zhan.service.DigitalMallSkuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DigitalMallSkuController {

    private DigitalMallSkuServiceImpl digitalMallSkuService;

    @Autowired
    public DigitalMallSkuController(DigitalMallSkuServiceImpl digitalMallSkuService) {
        this.digitalMallSkuService = digitalMallSkuService;
    }

    @RequestMapping("addSku")
    public void insert(DigitalMallSku sku){
        digitalMallSkuService.insert(sku);
    }

    @RequestMapping("getSkuList")
    public List<DigitalMallSku> selectAll(){
        return digitalMallSkuService.selectAll();
    }
}
