package com.zhan.service;

import com.zhan.console.service.DigitalMallCategoryService;
import com.zhan.dao.DigitalMallCategoryMapper;
import com.zhan.model.DigitalMallCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class DigitalMallCategoryServiceImpl implements DigitalMallCategoryService {

    private DigitalMallCategoryMapper digitalMallCategoryMapper;

    @Autowired
    public DigitalMallCategoryServiceImpl(DigitalMallCategoryMapper digitalMallCategoryMapper){
        this.digitalMallCategoryMapper = digitalMallCategoryMapper;
    }

    @Override
    public List<DigitalMallCategory> getCategoryList() {
        return digitalMallCategoryMapper.selectAll();
    }

    @Override
    public int insert(DigitalMallCategory record) {
        record.setUpdateTime(System.currentTimeMillis() + "");
        digitalMallCategoryMapper.insert(record);
        return record.getId();
    }

    @Override
    public DigitalMallCategory selectByName(String name) {
        return digitalMallCategoryMapper.selectByName(name);
    }
}
