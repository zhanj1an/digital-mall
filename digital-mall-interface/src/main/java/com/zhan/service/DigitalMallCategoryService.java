package com.zhan.service;

import com.zhan.model.DigitalMallCategory;

import java.util.List;

public interface DigitalMallCategoryService {
    List<DigitalMallCategory> getCategoryList();

    int insert(DigitalMallCategory record);

    DigitalMallCategory selectByName(String name);
}
