package com.zhan.dao;

import com.zhan.model.DigitalMallOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DigitalMallOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DigitalMallOrder record);

    int insertSelective(DigitalMallOrder record);

    DigitalMallOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DigitalMallOrder record);

    int updateByPrimaryKey(DigitalMallOrder record);

    List<DigitalMallOrder> selectOrderList();
}