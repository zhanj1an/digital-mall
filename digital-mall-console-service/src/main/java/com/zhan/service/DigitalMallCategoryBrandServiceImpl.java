package com.zhan.service;

import com.zhan.console.service.DigitalMallCategoryBrandService;
import com.zhan.dao.DigitalMallCategoryBrandMapper;
import com.zhan.model.DigitalMallCategoryBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("categoryBrandService")
public class DigitalMallCategoryBrandServiceImpl implements DigitalMallCategoryBrandService {

    private DigitalMallCategoryBrandMapper digitalMallCategoryBrandMapper;

    @Autowired
    public DigitalMallCategoryBrandServiceImpl(DigitalMallCategoryBrandMapper digitalMallCategoryBrandMapper) {
        this.digitalMallCategoryBrandMapper = digitalMallCategoryBrandMapper;
    }

    @Override
    public int insert(DigitalMallCategoryBrand record) {
        record.setUpdateTime(System.currentTimeMillis() + "");
        try {
            digitalMallCategoryBrandMapper.insert(record);
        }catch (Exception e){
            System.out.println("分类品牌存在");
        }
        return 1;
    }
}
