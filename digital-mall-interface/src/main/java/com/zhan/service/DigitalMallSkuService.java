package com.zhan.service;

import com.zhan.model.DigitalMallSku;

import java.util.List;

public interface DigitalMallSkuService {

    void insert(DigitalMallSku sku);

    List<DigitalMallSku> selectAll();
}
