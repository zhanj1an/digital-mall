package com.zhan.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DigitalMallGoods {
    private Integer id;

    private String name;

    private Integer categoryId;

    private Integer brandId;

    private String imageUrl;

    private List<String> imageUrlList;

    private String introduce;

    private String desUrl;

    private List<String> desUrlList;

    private Integer saleNum;

    private Integer isShow;

    private Integer isNew;

    private Integer goodsRank;

    private Integer isDelete;

    private String updateTime;

    private Integer pageNum = 0;

    private Integer pageSize = 10;

    private DigitalMallGoodsSynopsis goodsSynopsis;
}