package com.zhan.dao;

import com.zhan.model.DigitalMallCategoryBrand;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DigitalMallCategoryBrandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DigitalMallCategoryBrand record);

    int insertSelective(DigitalMallCategoryBrand record);

    DigitalMallCategoryBrand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DigitalMallCategoryBrand record);

    int updateByPrimaryKey(DigitalMallCategoryBrand record);
}