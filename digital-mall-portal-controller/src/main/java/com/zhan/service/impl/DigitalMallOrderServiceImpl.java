package com.zhan.service.impl;

import com.zhan.dao.DigitalMallGoodsMapper;
import com.zhan.dao.DigitalMallOrderGoodsMapper;
import com.zhan.dao.DigitalMallOrderMapper;
import com.zhan.dao.DigitalMallSkuMapper;
import com.zhan.model.*;
import com.zhan.service.DigitalMallCartService;
import com.zhan.service.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.List;

@Service
public class DigitalMallOrderServiceImpl {

    private DigitalMallOrderMapper digitalMallOrderMapper;

    private DigitalMallOrderGoodsMapper digitalMallOrderGoodsMapper;

    private DigitalMallSkuMapper digitalMallSkuMapper;

    private DigitalMallGoodsMapper digitalMallGoodsMapper;

    private GoodsRepository goodsRepository;

    private DigitalMallCartService digitalMallCartService;

    @Autowired
    public DigitalMallOrderServiceImpl(DigitalMallOrderMapper digitalMallOrderMapper,
                                       DigitalMallOrderGoodsMapper digitalMallOrderGoodsMapper,
                                       DigitalMallSkuMapper digitalMallSkuMapper,
                                       DigitalMallGoodsMapper digitalMallGoodsMapper,
                                       GoodsRepository goodsRepository,
                                       DigitalMallCartService digitalMallCartService) {
        this.digitalMallOrderMapper = digitalMallOrderMapper;
        this.digitalMallOrderGoodsMapper = digitalMallOrderGoodsMapper;
        this.digitalMallSkuMapper = digitalMallSkuMapper;
        this.digitalMallGoodsMapper = digitalMallGoodsMapper;
        this.goodsRepository = goodsRepository;
        this.digitalMallCartService = digitalMallCartService;
    }

    @Transactional
    public void insertOrder(DigitalMallOrder digitalMallOrder, DigitalMallCartInfo digitalMallCartInfo, ServletRequest servletRequest, ServletResponse servletResponse){
        digitalMallOrder.setIsPay(1);
        digitalMallOrder.setIsSend(0);
        digitalMallOrder.setCreateTime(System.currentTimeMillis() + "");
        digitalMallOrderMapper.insert(digitalMallOrder);
        int orderId = digitalMallOrder.getId();
        List<DigitalMallCartGoods> cartGoodsList = digitalMallCartInfo.getGoodsList();
        for(DigitalMallCartGoods goods : cartGoodsList){
            digitalMallOrderGoodsMapper.insert(new DigitalMallOrderGoods(goods.getSkuId(), goods.getGoodsName(),
                    goods.getPrice(), goods.getGoodsNum(), orderId));
            //修改库存
            DigitalMallSku sku = digitalMallSkuMapper.selectByPrimaryKey(goods.getSkuId());
            sku.setStock(sku.getStock() - goods.getGoodsNum());
            digitalMallSkuMapper.updateByPrimaryKey(sku);

            //修改销量
            DigitalMallGoods digitalMallGoods = digitalMallGoodsMapper.selectByPrimaryKey(sku.getGoodsId());
            digitalMallGoods.setSaleNum(digitalMallGoods.getSaleNum() + goods.getGoodsNum());
            digitalMallGoodsMapper.updateByPrimaryKey(digitalMallGoods);

            //修改es商品销量
            EsGoodsInfo goodsInfo = goodsRepository.findById(sku.getGoodsId()).orElse(null);
            if(goodsInfo != null){
                goodsInfo.setSaleNum(goodsInfo.getSaleNum() + goods.getGoodsNum());
                goodsRepository.save(goodsInfo);
            }
        }
        digitalMallCartService.deleteCart(servletRequest, servletResponse);
    }
}
