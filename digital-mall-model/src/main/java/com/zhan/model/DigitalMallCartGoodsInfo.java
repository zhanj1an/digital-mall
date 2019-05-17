package com.zhan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DigitalMallCartGoodsInfo {
    private Integer id;

    private Integer skuId;

    private Integer cartId;

    private String goodsName;

    private String goodsAttr;

    private String imgUrl;

    private Integer goodsNum;

    private Double goodsPrice;

    public DigitalMallCartGoodsInfo(Integer skuId, Integer cartId, String goodsName, String goodsAttr, String imgUrl, Integer goodsNum, Double goodsPrice) {
        this.skuId = skuId;
        this.cartId = cartId;
        this.goodsName = goodsName;
        this.goodsAttr = goodsAttr;
        this.imgUrl = imgUrl;
        this.goodsNum = goodsNum;
        this.goodsPrice = goodsPrice;
    }
}