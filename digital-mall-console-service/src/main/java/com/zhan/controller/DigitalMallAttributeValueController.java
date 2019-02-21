package com.zhan.controller;

import com.zhan.model.DigitalMallAttributeValue;
import com.zhan.service.DigitalMallAttributeValueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DigitalMallAttributeValueController {

    private DigitalMallAttributeValueServiceImpl digitalMallAttributeValueService;

    @Autowired
    public DigitalMallAttributeValueController(DigitalMallAttributeValueServiceImpl digitalMallAttributeValueService) {
        this.digitalMallAttributeValueService = digitalMallAttributeValueService;
    }

    @RequestMapping("addAttributeValue")
    public void insert(DigitalMallAttributeValue digitalMallAttributeValue){
        digitalMallAttributeValueService.insert(digitalMallAttributeValue);
    }
}
