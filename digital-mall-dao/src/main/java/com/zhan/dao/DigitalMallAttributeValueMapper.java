package com.zhan.dao;

import com.zhan.model.DigitalMallAttributeValue;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DigitalMallAttributeValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DigitalMallAttributeValue record);

    int insertSelective(DigitalMallAttributeValue record);

    DigitalMallAttributeValue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DigitalMallAttributeValue record);

    int updateByPrimaryKey(DigitalMallAttributeValue record);
}