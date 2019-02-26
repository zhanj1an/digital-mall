package com.zhan.service;

import com.zhan.dao.DigitalMallSkuMapper;
import com.zhan.model.DigitalMallSku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("skuService")
public class DigitalMallSkuServiceImpl implements DigitalMallSkuService {

    private DigitalMallSkuMapper digitalMallSkuMapper;

    @Autowired
    public DigitalMallSkuServiceImpl(DigitalMallSkuMapper digitalMallSkuMapper) {
        this.digitalMallSkuMapper = digitalMallSkuMapper;
    }

    @Override
    public void insert(DigitalMallSku sku) {
        digitalMallSkuMapper.insert(sku);
    }

    @Override
    public List<DigitalMallSku> selectAll() {
        return digitalMallSkuMapper.selectAll();
    }
}
