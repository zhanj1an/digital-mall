package com.zhan.controller;

import com.zhan.model.DigitalMallCategoryBrand;
import com.zhan.service.DigitalMallCategoryBrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DigitalMallCategoryBrandController {

    private DigitalMallCategoryBrandServiceImpl digitalMallCategoryBrandService;

    @Autowired
    public DigitalMallCategoryBrandController(DigitalMallCategoryBrandServiceImpl digitalMallCategoryBrandService) {
        this.digitalMallCategoryBrandService = digitalMallCategoryBrandService;
    }

    @RequestMapping("/insertCategoryBrand")
    public int insert(DigitalMallCategoryBrand record){
        return digitalMallCategoryBrandService.insert(record);
    }
}
