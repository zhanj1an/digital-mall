package com.zhan.dao;

import com.zhan.model.DigitalMallGoods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DigitalMallGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DigitalMallGoods record);

    int insertSelective(DigitalMallGoods record);

    DigitalMallGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DigitalMallGoods record);

    int updateByPrimaryKey(DigitalMallGoods record);

    List<DigitalMallGoods> selectAll();

    List<DigitalMallGoods> selectGoodsByCriteria(DigitalMallGoods goods);

    List<DigitalMallGoods> selectGoodsOrderByGoodsRank(DigitalMallGoods goods);

    List<DigitalMallGoods> selectNewGoodsOrderByGoodsRank();
}