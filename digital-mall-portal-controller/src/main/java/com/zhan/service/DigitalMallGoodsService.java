package com.zhan.service;

import com.zhan.model.*;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("portalGoodsService")
public class DigitalMallGoodsService {

    private RestTemplate restTemplate;

    private GoodsRepository goodsRepository;

    @Autowired
    public DigitalMallGoodsService(RestTemplate restTemplate,
                                   GoodsRepository goodsRepository) {
        this.restTemplate = restTemplate;
        this.goodsRepository = goodsRepository;
    }

    @SuppressWarnings("unchecked")
    public List<DigitalMallGoodsSynopsis> getRollGoodsSynopsis(){
        return restTemplate.getForObject("http://portal-service/getRollGoodsSynopsis", List.class);
    }

    @SuppressWarnings("unchecked")
    public List<DigitalMallGoodsAttribute> getGoodsAttribute(int goodsId){
        return restTemplate.getForObject("http://portal-service/getGoodsAttribute?goodsId=" + goodsId, List.class);
    }

    public DigitalMallSku queryGoodsStock(int goodsId, String attribute){
        return restTemplate.getForObject("http://portal-service/queryGoodsStock" +
                "?goodsId=" + goodsId +
                "&attribute=" + attribute, DigitalMallSku.class);
    }

    public Page<EsGoodsInfo> selectGoodsByCriteria(DigitalMallGoods goods){
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        if(goods.getName() != null && !"".equals(goods.getName()) && !"null".equals(goods.getName())){
            queryBuilder.must(QueryBuilders.termQuery("goodsName", goods.getName()));
        }
        if(goods.getBrandId() != null){
            queryBuilder.must(QueryBuilders.termQuery("brandId", goods.getBrandId()));
        }
        if(goods.getCategoryId() != null){
            queryBuilder.must(QueryBuilders.termQuery("categoryId", goods.getCategoryId()));
        }

        Pageable pageable = PageRequest.of(goods.getPageNum(), 9, new Sort(Sort.Direction.DESC, "goodsRank"));

        return goodsRepository.search(queryBuilder, pageable);
    }
}
