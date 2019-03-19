package com.zhan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhan.console.service.DigitalMallGoodsService;
import com.zhan.dao.*;
import com.zhan.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("goodsService")
public class DigitalMallGoodsServiceImpl implements DigitalMallGoodsService {

    private DigitalMallGoodsMapper digitalMallGoodsMapper;

    private DigitalMallAttributeMapper digitalMallAttributeMapper;

    private DigitalMallAttributeValueMapper digitalMallAttributeValueMapper;

    private DigitalMallCategoryMapper digitalMallCategoryMapper;

    private DigitalMallSkuMapper digitalMallSkuMapper;

    private DigitalMallBrandMapper digitalMallBrandMapper;

    private GoodsRepository goodsRepository;

    @Autowired
    public DigitalMallGoodsServiceImpl(DigitalMallGoodsMapper digitalMallGoodsMapper,
                                       DigitalMallAttributeMapper digitalMallAttributeMapper,
                                       DigitalMallAttributeValueMapper digitalMallAttributeValueMapper,
                                       DigitalMallCategoryMapper digitalMallCategoryMapper,
                                       DigitalMallSkuMapper digitalMallSkuMapper,
                                       DigitalMallBrandMapper digitalMallBrandMapper,
                                       GoodsRepository goodsRepository){
        this.digitalMallGoodsMapper = digitalMallGoodsMapper;
        this.digitalMallAttributeMapper = digitalMallAttributeMapper;
        this.digitalMallAttributeValueMapper = digitalMallAttributeValueMapper;
        this.digitalMallCategoryMapper = digitalMallCategoryMapper;
        this.digitalMallSkuMapper = digitalMallSkuMapper;
        this.digitalMallBrandMapper = digitalMallBrandMapper;
        this.goodsRepository = goodsRepository;
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

        //获得商品属性值集合
        List<DigitalMallAttribute> attributeList = digitalMallAttributeMapper.selectByGoodsId(id);
        List<DigitalMallGoodsAttribute> goodsAttributeList = new ArrayList<>();
        for(DigitalMallAttribute attribute : attributeList){
            //商品每个属性值集合
            List<DigitalMallAttributeValue> attributeValueList = digitalMallAttributeValueMapper.selectByAttributeId(attribute.getId());
            if(attributeValueList.size() > 1) {
                List<String> attributeValues = new ArrayList<>();
                for (DigitalMallAttributeValue attributeValue : attributeValueList) {
                    attributeValues.add(attributeValue.getAttrValue());
                }
                //获得每个属性的名字和对应的值集合
                goodsAttributeList.add(new DigitalMallGoodsAttribute(attribute.getName(), attributeValues));
            }
        }

        //获得商品分类名字
        String categoryName = digitalMallCategoryMapper.selectByPrimaryKey(goods.getCategoryId()).getName();
        String brandName = digitalMallBrandMapper.selectByPrimaryKey(goods.getBrandId()).getName();

        double lowestPrice = Double.MAX_VALUE;
        double highestPrice = Double.MIN_VALUE;

        List<DigitalMallSku> skuList = digitalMallSkuMapper.selectSkuByCriteria(DigitalMallSku.builder().goodsId(id).build());

        for(DigitalMallSku sku : skuList){
            lowestPrice = sku.getPrice() < lowestPrice ? sku.getPrice() : lowestPrice;
            highestPrice = sku.getPrice() > highestPrice ? sku.getPrice() : highestPrice;
        }

        List<String> imgUrlList = Arrays.asList(goods.getImageUrl().split(","));
        List<String> desUrlList = Arrays.asList(goods.getDesUrl().split(","));

        EsGoodsInfo esGoodsInfo = new EsGoodsInfo(id, goods.getName(), goods.getCategoryId(), categoryName, goods.getBrandId(),
                brandName, goods.getSaleNum(), goods.getIsNew(), goods.getGoodsRank(), lowestPrice, highestPrice,
                goods.getIntroduce(), imgUrlList, desUrlList, goodsAttributeList);

        //数据存入es
        goodsRepository.save(esGoodsInfo);

        return digitalMallGoodsMapper.updateByPrimaryKey(goods);
    }

    @Override
    public Integer hideGoods(int id) {
        DigitalMallGoods goods = digitalMallGoodsMapper.selectByPrimaryKey(id);
        goods.setIsShow(0);
        goodsRepository.deleteById(id);
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
        if(pageNum == 0 ){
            pageNum = 1;
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
