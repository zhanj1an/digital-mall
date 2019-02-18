package com.zhan.dao;

import com.zhan.model.DigitalMallCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DigitalMallCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DigitalMallCategory record);

    int insertSelective(DigitalMallCategory record);

    DigitalMallCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DigitalMallCategory record);

    int updateByPrimaryKey(DigitalMallCategory record);

    List<DigitalMallCategory> selectAll();

    DigitalMallCategory selectByName(String name);
}