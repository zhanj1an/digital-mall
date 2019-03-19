package com.zhan.service;

import com.zhan.model.EsGoodsInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GoodsRepository extends ElasticsearchRepository<EsGoodsInfo, Integer> {
}
