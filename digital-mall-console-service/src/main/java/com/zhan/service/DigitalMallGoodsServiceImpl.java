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
        goodsList.forEach(goods ->{
            goods.setImageUrlList(Arrays.asList(goods.getImageUrl().split(",")));
            goods.setImageUrl(goods.getImageUrlList().get(0));

            goods.setDesUrlList(Arrays.asList(goods.getDesUrl().split(",")));
            goods.setDesUrl(goods.getDesUrlList().get(0));
        });
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
    public Integer deleteGoods(int id) {
        DigitalMallGoods goods = digitalMallGoodsMapper.selectByPrimaryKey(id);
        goods.setIsDelete(1);
        return digitalMallGoodsMapper.updateByPrimaryKey(goods);
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

    @Override
    public Integer showGoods(int id) {
        DigitalMallGoods goods = digitalMallGoodsMapper.selectByPrimaryKey(id);
        goods.setIsShow(1);
        return digitalMallGoodsMapper.updateByPrimaryKey(goods);
    }

    @Override
    public Integer hideGoods(int id) {
        DigitalMallGoods goods = digitalMallGoodsMapper.selectByPrimaryKey(id);
        goods.setIsShow(0);
        return digitalMallGoodsMapper.updateByPrimaryKey(goods);
    }

    @Override
    public List<DigitalMallGoods> selectGoodsByCriteria(DigitalMallGoods digitalMallGoods) {
        List<DigitalMallGoods> goodsList = digitalMallGoodsMapper.selectGoodsByCriteria(digitalMallGoods);
        goodsList.forEach(goods ->{
            goods.setImageUrlList(Arrays.asList(goods.getImageUrl().split(",")));
            goods.setImageUrl(goods.getImageUrlList().get(0));

            goods.setDesUrlList(Arrays.asList(goods.getDesUrl().split(",")));
            goods.setDesUrl(goods.getDesUrlList().get(0));
        });
        return goodsList;
    }
}
