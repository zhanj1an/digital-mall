package com.zhan.dao;

import com.zhan.model.DigitalMallAttribute;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DigitalMallAttributeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DigitalMallAttribute record);

    int insertSelective(DigitalMallAttribute record);

    DigitalMallAttribute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DigitalMallAttribute record);

    int updateByPrimaryKey(DigitalMallAttribute record);
}