package com.zhan.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DigitalMallSku {
    private Integer id;

    private Integer goodsId;

    private String goodsName;

    private String attribute;

    private Double price;

    private Integer stock;

    private Integer isDelete;

    private String updateTime;

    public DigitalMallSku(Integer goodsId, String goodsName, String attribute, Double price, Integer stock, Integer isDelete, String updateTime) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.attribute = attribute;
        this.price = price;
        this.stock = stock;
        this.isDelete = isDelete;
        this.updateTime = updateTime;
    }
}