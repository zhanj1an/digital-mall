package com.zhan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "digital-mall-goods", type = "show-goods")
public class EsGoodsInfo {
    @Id
    private Integer goodsId;
    private String goodsName;
    private Integer categoryId;
    private String categoryName;
    private Integer brandId;
    private String brandName;
    private Integer saleNum;
    private Integer isNew;
    private Integer goodsRank;
    private Double lowestPrice;
    private Double highestPrice;
    private String goodsIntroduce;
    private List<String> imgUrlList;
    private List<String> desUrlList;
    private List<DigitalMallGoodsAttribute> attributeList;
}
