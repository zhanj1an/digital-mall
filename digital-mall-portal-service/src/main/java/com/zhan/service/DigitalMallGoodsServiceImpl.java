package com.zhan.service;

import com.zhan.dao.*;
import com.zhan.model.*;
import com.zhan.portal.service.DigitalMallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("portalGoodsService")
public class DigitalMallGoodsServiceImpl implements DigitalMallGoodsService {

    private DigitalMallGoodsMapper digitalMallGoodsMapper;

    private DigitalMallSkuMapper digitalMallSkuMapper;

    private DigitalMallCategoryMapper digitalMallCategoryMapper;

    private DigitalMallAttributeMapper digitalMallAttributeMapper;

    private DigitalMallAttributeValueMapper digitalMallAttributeValueMapper;

    @Autowired
    public DigitalMallGoodsServiceImpl(DigitalMallGoodsMapper digitalMallGoodsMapper,
                                       DigitalMallSkuMapper digitalMallSkuMapper,
                                       DigitalMallCategoryMapper digitalMallCategoryMapper,
                                       DigitalMallAttributeMapper digitalMallAttributeMapper,
                                       DigitalMallAttributeValueMapper digitalMallAttributeValueMapper) {
        this.digitalMallGoodsMapper = digitalMallGoodsMapper;
        this.digitalMallSkuMapper = digitalMallSkuMapper;
        this.digitalMallCategoryMapper = digitalMallCategoryMapper;
        this.digitalMallAttributeMapper = digitalMallAttributeMapper;
        this.digitalMallAttributeValueMapper = digitalMallAttributeValueMapper;
    }

    @Override
    public List<DigitalMallGoodsSynopsis> getRollGoodsSynopsis() {
        List<DigitalMallGoodsSynopsis> goodsSynopsisList = new ArrayList<>();
        List<DigitalMallGoods> goodsList = digitalMallGoodsMapper.selectAll();
        List<DigitalMallCategory> categoryList = digitalMallCategoryMapper.selectAll();
        //获取五个滚动显示的商品简介信息
        for (int i = 0; i < 5; i++) {
            int goodsId = goodsList.get(i).getId();
            String goodsName = goodsList.get(i).getName();
            List<String> imgUrlList = Arrays.asList(goodsList.get(i).getImageUrl().split(","));
            List<String> desUrlList = Arrays.asList(goodsList.get(i).getDesUrl().split(","));
            String category = "";
            String goodsIntroduce = goodsList.get(i).getIntroduce();
            double lowestPrice = Double.MAX_VALUE;
            double highestPrice = Double.MIN_VALUE;
            for(DigitalMallCategory c : categoryList){
                if(goodsList.get(i).getCategoryId().equals(c.getId())){
                    category = c.getName();
                }
            }

            List<DigitalMallSku> skuList = digitalMallSkuMapper.selectSkuByCriteria(DigitalMallSku.builder().goodsId(goodsId).build());

            for(DigitalMallSku sku : skuList){
                lowestPrice = sku.getPrice() < lowestPrice ? sku.getPrice() : lowestPrice;
                highestPrice = sku.getPrice() > highestPrice ? sku.getPrice() : highestPrice;
            }
            goodsSynopsisList.add(new DigitalMallGoodsSynopsis(goodsId, goodsName, category, lowestPrice, highestPrice, goodsIntroduce, imgUrlList, desUrlList));
        }
        return goodsSynopsisList;
    }

    @Override
    public List<DigitalMallGoodsAttribute> getGoodsAttribute(int goodsId) {
        //商品所拥有属性集合
        List<DigitalMallAttribute> attributeList = digitalMallAttributeMapper.selectByGoodsId(goodsId);
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
        return goodsAttributeList;
    }
}
