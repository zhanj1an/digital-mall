package com.zhan.controller;

import com.zhan.model.DigitalMallBrand;
import com.zhan.service.DigitalMallBrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DigitalMallBrandController {

    private DigitalMallBrandServiceImpl digitalMallBrandService;

    @Autowired
    public DigitalMallBrandController(DigitalMallBrandServiceImpl digitalMallBrandService) {
        this.digitalMallBrandService = digitalMallBrandService;
    }

    @RequestMapping("/getBrandList")
    public List<DigitalMallBrand> getBrandList(){
        return digitalMallBrandService.getBrandList();
    }

    @RequestMapping("/insertBrand")
    public int insert(DigitalMallBrand brand){
        return digitalMallBrandService.insert(brand);
    }
}
