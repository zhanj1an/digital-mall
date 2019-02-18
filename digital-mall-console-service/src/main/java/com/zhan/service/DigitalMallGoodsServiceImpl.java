package com.zhan.service;

import com.zhan.dao.DigitalMallGoodsMapper;
import com.zhan.model.DigitalMallGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service("goodsService")
public class DigitalMallGoodsServiceImpl implements DigitalMallGoodsService {

    private DigitalMallGoodsMapper digitalMallGoodsMapper;

    @Autowired
    public DigitalMallGoodsServiceImpl(DigitalMallGoodsMapper digitalMallGoodsMapper){
        this.digitalMallGoodsMapper = digitalMallGoodsMapper;
    }

    @Override
    public List<DigitalMallGoods> getGoodsList() {
        List<DigitalMallGoods> goodsList = digitalMallGoodsMapper.selectAll();
        for(DigitalMallGoods d : goodsList){
            d.setImageUrlList(Arrays.asList(d.getImageUrl().split(",")));
            d.setImageUrl(d.getImageUrlList().get(0));

            d.setDesUrlList(Arrays.asList(d.getDesUrl().split(",")));
            d.setDesUrl(d.getDesUrlList().get(0));
        }
        return goodsList;
    }

    @Override
    public void addGoods(DigitalMallGoods digitalMallGoods) {
        digitalMallGoods.setIsDelete(0);
        digitalMallGoods.setIsShow(0);
        digitalMallGoods.setSaleNum(0);
        digitalMallGoods.setUpdateTime(System.currentTimeMillis() + "");
        digitalMallGoodsMapper.insert(digitalMallGoods);
    }

    @Override
    public void deleteGoods(int id) {
        DigitalMallGoods goods = digitalMallGoodsMapper.selectByPrimaryKey(id);
        goods.setIsDelete(1);
        digitalMallGoodsMapper.updateByPrimaryKey(goods);
    }

    @Override
    public DigitalMallGoods getGoodsById(int id) {
        return digitalMallGoodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateGoods(DigitalMallGoods goods) {
        goods.setUpdateTime(System.currentTimeMillis() + "");
        digitalMallGoodsMapper.updateByPrimaryKey(goods);
    }


}
