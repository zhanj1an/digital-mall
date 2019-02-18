package com.zhan.dao;

import com.zhan.model.DigitalMallAttribute;

public interface DigitalMallAttributeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DigitalMallAttribute record);

    int insertSelective(DigitalMallAttribute record);

    DigitalMallAttribute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DigitalMallAttribute record);

    int updateByPrimaryKey(DigitalMallAttribute record);
}