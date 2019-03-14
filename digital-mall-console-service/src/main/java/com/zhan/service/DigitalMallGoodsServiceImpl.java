package com.zhan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhan.console.service.DigitalMallGoodsService;
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
    public Integer setGoodsIsNew(int id) {
        DigitalMallGoods goods = digitalMallGoodsMapper.selectByPrimaryKey(id);
        goods.setIsNew(1);
        return digitalMallGoodsMapper.updateByPrimaryKey(goods);
    }

    @Override
    public Integer setGoodsIsOld(int id) {
        DigitalMallGoods goods = digitalMallGoodsMapper.selectByPrimaryKey(id);
        goods.setIsNew(0);
        return digitalMallGoodsMapper.updateByPrimaryKey(goods);
    }

    @Override
    public PageInfo<DigitalMallGoods> selectGoodsByCriteria(String name, String brandId, String categoryId, int pageNum, int pageSize) {
        DigitalMallGoods digitalMallGoods = new DigitalMallGoods();
        if(name != null && !"".equals(name) && !"null".equals(name)){
            digitalMallGoods.setName(name);
        }
        if(brandId != null && !"".equals(brandId) && !"null".equals(brandId)){
            digitalMallGoods.setBrandId(Integer.parseInt(brandId));
        }
        if(categoryId != null && !"".equals(categoryId) && !"null".equals(categoryId)){
            digitalMallGoods.setCategoryId(Integer.parseInt(categoryId));
        }
        PageHelper.startPage(pageNum, pageSize);
        List<DigitalMallGoods> goodsList = digitalMallGoodsMapper.selectGoodsByCriteria(digitalMallGoods);
        PageInfo<DigitalMallGoods> pageInfo = new PageInfo<>(goodsList);
        pageInfo.getList().forEach(goods ->{
            goods.setImageUrlList(Arrays.asList(goods.getImageUrl().split(",")));
            goods.setImageUrl(goods.getImageUrlList().get(0));

            goods.setDesUrlList(Arrays.asList(goods.getDesUrl().split(",")));
            goods.setDesUrl(goods.getDesUrlList().get(0));
        });
        return pageInfo;
    }
}
