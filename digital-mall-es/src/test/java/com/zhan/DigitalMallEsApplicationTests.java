package com.zhan;

import com.alibaba.fastjson.JSON;
import com.zhan.service.GoodsRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application.properties")
public class DigitalMallEsApplicationTests {

    @Autowired
    GoodsRepository goodsRepository;

    @Test
    public void contextLoads() {
        Pageable pageable = PageRequest.of(0, 5, new Sort(Sort.Direction.DESC, "goodsRank"));


        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("brand", 1))
                .must(QueryBuilders.termQuery("categoryId", 2));
        goodsRepository.search(queryBuilder, pageable).forEach(g ->
                System.out.println(JSON.toJSONString(g)));
    }

}
