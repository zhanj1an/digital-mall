package com.zhan.service;

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
}
