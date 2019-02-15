package com.zhan.controller;

import com.zhan.model.DigitalMallCategory;
import com.zhan.service.DigitalMallCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DigitalMallCategoryController {

    private DigitalMallCategoryServiceImpl digitalMallCategoryService;

    @Autowired
    public DigitalMallCategoryController(DigitalMallCategoryServiceImpl digitalMallCategoryService) {
        this.digitalMallCategoryService = digitalMallCategoryService;
    }

    @RequestMapping("/getCategoryList")
    public List<DigitalMallCategory> getCategoryList(){
        return digitalMallCategoryService.getCategoryList();
    }

    @RequestMapping("/insertCategory")
    public int insert(DigitalMallCategory record){
        return digitalMallCategoryService.insert(record);
    }
}
