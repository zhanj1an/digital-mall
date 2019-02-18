package com.zhan.service;

import com.zhan.model.DigitalMallBrand;

import java.util.List;

public interface DigitalMallBrandService {
    List<DigitalMallBrand> getBrandList();

    int insert(DigitalMallBrand record);

    DigitalMallBrand selectByName(String name);
}
