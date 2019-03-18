package com.zhan.service;

import com.zhan.model.GoodsTest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GoodsRepository extends ElasticsearchRepository<GoodsTest, Integer> {
}
