package com.zhan.service;

import com.github.pagehelper.PageInfo;
import com.zhan.model.DigitalMallGoods;

import java.util.List;

public interface DigitalMallGoodsService {

    List<DigitalMallGoods> getGoodsList();

    void addGoods(DigitalMallGoods digitalMallGoods);

    Integer deleteGoods(int id);

    DigitalMallGoods getGoodsById(int id);

    void updateGoods(DigitalMallGoods goods);

    Integer showGoods(int id);

    Integer hideGoods(int id);

    PageInfo<DigitalMallGoods> selectGoodsByCriteria(String name, String brandId, String categoryId, int pageNum, int pageSize);
}
