package com.zhan.portal.service;

import com.zhan.model.DigitalMallGoodsAttribute;
import com.zhan.model.DigitalMallGoodsSynopsis;

import java.util.List;

public interface DigitalMallGoodsService {

    List<DigitalMallGoodsSynopsis> getRollGoodsSynopsis();

    List<DigitalMallGoodsAttribute> getGoodsAttribute(int goodsId);
}
