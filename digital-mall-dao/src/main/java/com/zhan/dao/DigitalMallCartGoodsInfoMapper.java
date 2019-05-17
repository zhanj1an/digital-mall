package com.zhan.dao;

import com.zhan.model.DigitalMallCartGoodsInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DigitalMallCartGoodsInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DigitalMallCartGoodsInfo record);

    int insertSelective(DigitalMallCartGoodsInfo record);

    DigitalMallCartGoodsInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DigitalMallCartGoodsInfo record);

    int updateByPrimaryKey(DigitalMallCartGoodsInfo record);

    List<DigitalMallCartGoodsInfo> selectByCartId(int cartId);

    DigitalMallCartGoodsInfo selectBySkuId(int skuId);

    int deleteByCartId(int userId);
}