package com.zhan.service;

import com.zhan.dao.DigitalMallOrderGoodsMapper;
import com.zhan.dao.DigitalMallOrderMapper;
import com.zhan.model.DigitalMallOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DigitalMallOrderService {

    private DigitalMallOrderMapper digitalMallOrderMapper;

    private DigitalMallOrderGoodsMapper digitalMallOrderGoodsMapper;

    @Autowired
    public DigitalMallOrderService(DigitalMallOrderMapper digitalMallOrderMapper,
                                   DigitalMallOrderGoodsMapper digitalMallOrderGoodsMapper) {
        this.digitalMallOrderMapper = digitalMallOrderMapper;
        this.digitalMallOrderGoodsMapper = digitalMallOrderGoodsMapper;
    }

    public List<DigitalMallOrder> getOrderList(){
        return digitalMallOrderMapper.selectOrderList();
    }
}
