package com.zhan.portal.service;

import com.zhan.model.DigitalMallGoodsAttribute;
import com.zhan.model.DigitalMallGoodsSynopsis;
import com.zhan.model.DigitalMallSku;

import java.util.List;

public interface DigitalMallGoodsService {

    List<DigitalMallGoodsSynopsis> getRollGoodsSynopsis();

    List<DigitalMallGoodsAttribute> getGoodsAttribute(int goodsId);

    DigitalMallSku queryGoodsStock(int goodsId, String attribute);
}
