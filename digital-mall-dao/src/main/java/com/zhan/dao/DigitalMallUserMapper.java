package com.zhan.dao;

import com.zhan.model.DigitalMallUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DigitalMallUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DigitalMallUser record);

    int insertSelective(DigitalMallUser record);

    DigitalMallUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DigitalMallUser record);

    int updateByPrimaryKey(DigitalMallUser record);

    DigitalMallUser selectAdminUser(DigitalMallUser user);

    DigitalMallUser selectGeneralUser(DigitalMallUser user);
}