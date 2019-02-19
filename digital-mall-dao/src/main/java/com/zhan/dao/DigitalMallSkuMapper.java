package com.zhan.dao;

import com.zhan.model.DigitalMallSku;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DigitalMallSkuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DigitalMallSku record);

    int insertSelective(DigitalMallSku record);

    DigitalMallSku selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DigitalMallSku record);

    int updateByPrimaryKey(DigitalMallSku record);
}