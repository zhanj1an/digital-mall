package com.zhan.dao;

import com.zhan.model.DigitalMallAttribute;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DigitalMallAttributeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DigitalMallAttribute record);

    int insertSelective(DigitalMallAttribute record);

    DigitalMallAttribute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DigitalMallAttribute record);

    int updateByPrimaryKey(DigitalMallAttribute record);

    List<DigitalMallAttribute> selectByGoodsId(int goodsId);
}