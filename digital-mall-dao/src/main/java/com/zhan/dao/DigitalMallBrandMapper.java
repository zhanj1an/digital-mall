package com.zhan.dao;

import com.zhan.model.DigitalMallBrand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DigitalMallBrandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DigitalMallBrand record);

    int insertSelective(DigitalMallBrand record);

    DigitalMallBrand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DigitalMallBrand record);

    int updateByPrimaryKey(DigitalMallBrand record);

    List<DigitalMallBrand> selectAll();

    DigitalMallBrand selectByName(String name);
}