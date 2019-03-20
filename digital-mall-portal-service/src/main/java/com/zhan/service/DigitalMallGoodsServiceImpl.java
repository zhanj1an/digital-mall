package com.zhan.service;

import com.zhan.dao.*;
import com.zhan.model.*;
import com.zhan.portal.service.DigitalMallGoodsService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("portalGoodsService")
public class DigitalMallGoodsServiceImpl implements DigitalMallGoodsService {

    private DigitalMallSkuMapper digitalMallSkuMapper;

    private GoodsRepository goodsRepository;

    @Autowired
    public DigitalMallGoodsServiceImpl(DigitalMallSkuMapper digitalMallSkuMapper,
                                       GoodsRepository goodsRepository) {
        this.digitalMallSkuMapper = digitalMallSkuMapper;
        this.goodsRepository = goodsRepository;
    }

    @Override
    public List<DigitalMallGoodsSynopsis> getRollGoodsSynopsis() {

        QueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery("isNew", 1);
        Pageable pageable = PageRequest.of(0, 5, new Sort(Sort.Direction.DESC, "goodsRank"));
        List<EsGoodsInfo> goodsInfos = goodsRepository.search(queryBuilder, pageable).getContent();
        List<DigitalMallGoodsSynopsis> goodsSynopsisList = new ArrayList<>();

        //获取五个滚动显示的商品简介信息
        for (EsGoodsInfo goodsInfo : goodsInfos) {
            goodsSynopsisList.add(new DigitalMallGoodsSynopsis(goodsInfo.getGoodsId(), goodsInfo.getGoodsName(), goodsInfo.getCategoryName(),
                    goodsInfo.getLowestPrice(), goodsInfo.getHighestPrice(), goodsInfo.getGoodsIntroduce(),
                    goodsInfo.getImgUrlList(), goodsInfo.getDesUrlList()));
        }
        return goodsSynopsisList;
    }

    @Override
    public List<DigitalMallGoodsAttribute> getGoodsAttribute(int goodsId) {
        //商品所拥有属性集合
        return goodsRepository.findById(goodsId).orElse(new EsGoodsInfo()).getAttributeList();
    }

    @Override
    public DigitalMallSku queryGoodsStock(int goodsId, String attribute) {
        List<DigitalMallSku> skuList = digitalMallSkuMapper.selectSkuByCriteria(DigitalMallSku.builder().goodsId(goodsId).build());
        for(DigitalMallSku sku : skuList) {
            if (Arrays.asList(sku.getAttribute().split(",")).containsAll(Arrays.asList(attribute.split(",")))) {
                return sku;
            }
        }
        return null;
    }
}
