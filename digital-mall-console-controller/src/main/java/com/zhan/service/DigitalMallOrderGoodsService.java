package com.zhan.service;

import com.zhan.dao.DigitalMallOrderGoodsMapper;
import com.zhan.model.DigitalMallOrderGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DigitalMallOrderGoodsService {

    private DigitalMallOrderGoodsMapper digitalMallOrderGoodsMapper;

    @Autowired
    public DigitalMallOrderGoodsService(DigitalMallOrderGoodsMapper digitalMallOrderGoodsMapper) {
        this.digitalMallOrderGoodsMapper = digitalMallOrderGoodsMapper;
    }

    public List<DigitalMallOrderGoods> getOrderGoodList(String id){
        return digitalMallOrderGoodsMapper.selectByOrderId(Integer.parseInt(id));
    }
}
