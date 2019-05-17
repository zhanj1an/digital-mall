package com.zhan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DigitalMallOrderGoods {
    private Integer id;

    private Integer skuId;

    private String goodsName;

    private Double goodsPrice;

    private Integer goodsNum;

    private Integer orderId;

    public DigitalMallOrderGoods(Integer skuId, String goodsName, Double goodsPrice, Integer goodsNum, Integer orderId) {
        this.skuId = skuId;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.goodsNum = goodsNum;
        this.orderId = orderId;
    }
}