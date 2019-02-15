package com.zhan.service;

import com.zhan.dao.DigitalMallBrandMapper;
import com.zhan.model.DigitalMallBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("brandService")
public class DigitalMallBrandServiceImpl implements DigitalMallBrandService{

    private DigitalMallBrandMapper digitalMallBrandMapper;

    @Autowired
    public DigitalMallBrandServiceImpl(DigitalMallBrandMapper digitalMallBrandMapper) {
        this.digitalMallBrandMapper = digitalMallBrandMapper;
    }

    @Override
    public List<DigitalMallBrand> getBrandList() {
        return digitalMallBrandMapper.selectAll();
    }

    @Override
    public int insert(DigitalMallBrand record) {
        record.setUpdateTime(System.currentTimeMillis() + "");
        digitalMallBrandMapper.insert(record);
        return record.getId();
    }

    @Override
    public DigitalMallBrand selectByName(String name) {
        return digitalMallBrandMapper.selectByName(name);
    }
}
