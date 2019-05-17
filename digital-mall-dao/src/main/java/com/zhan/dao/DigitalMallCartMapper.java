package com.zhan.dao;

import com.zhan.model.DigitalMallCart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DigitalMallCartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DigitalMallCart record);

    int insertSelective(DigitalMallCart record);

    DigitalMallCart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DigitalMallCart record);

    int updateByPrimaryKey(DigitalMallCart record);

    DigitalMallCart selectByUserId(int userId);

    int deleteByUserId(int userId);
}