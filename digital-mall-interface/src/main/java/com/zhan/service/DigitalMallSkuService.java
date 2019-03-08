package com.zhan.service;

import com.github.pagehelper.PageInfo;
import com.zhan.model.DigitalMallSku;

import java.util.List;

public interface DigitalMallSkuService {

    void insert(DigitalMallSku sku);

    List<DigitalMallSku> selectAll();

    void updateSku(int id, int stock);

    DigitalMallSku selectSkuById(int id);

    Integer deleteSku(int id);

    PageInfo<DigitalMallSku> selectSkuByCriteria(String isDelete, String goodsName, int pageNum, int pageSize);
}
