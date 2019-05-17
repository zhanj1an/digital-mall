package com.zhan.dao;

import com.zhan.model.DigitalMallOrderGoods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DigitalMallOrderGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DigitalMallOrderGoods record);

    int insertSelective(DigitalMallOrderGoods record);

    DigitalMallOrderGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DigitalMallOrderGoods record);

    int updateByPrimaryKey(DigitalMallOrderGoods record);

    List<DigitalMallOrderGoods> selectByOrderId(int orderId);
}