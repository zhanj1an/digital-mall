package com.zhan.controller;

import com.zhan.model.DigitalMallAttribute;
import com.zhan.service.DigitalMallAttributeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DigitalMallAttributeController {

    private DigitalMallAttributeServiceImpl digitalMallAttributeService;

    @Autowired
    public DigitalMallAttributeController(DigitalMallAttributeServiceImpl digitalMallAttributeService) {
        this.digitalMallAttributeService = digitalMallAttributeService;
    }

    @RequestMapping("addAttribute")
    public int insert(DigitalMallAttribute digitalMallAttribute){
        return digitalMallAttributeService.insert(digitalMallAttribute);
    }
}
