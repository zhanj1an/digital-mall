package com.zhan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@Document(indexName = "goods-test", type = "goods")
public class GoodsTest {

    private int id;
    private String name;
}
