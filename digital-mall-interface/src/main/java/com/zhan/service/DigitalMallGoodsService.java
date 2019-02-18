package com.zhan.service;

import com.zhan.model.DigitalMallGoods;

import java.util.List;

public interface DigitalMallGoodsService {

    List<DigitalMallGoods> getGoodsList();

    void addGoods(DigitalMallGoods digitalMallGoods);
}
