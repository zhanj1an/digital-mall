package com.zhan.model;

import lombok.Data;

@Data
public class DigitalMallSku {
    private Integer id;

    private Integer goodsId;

    private String goodsName;

    private String attribute;

    private Double price;

    private Integer stock;

    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute == null ? null : attribute.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public DigitalMallSku() {
    }

    public DigitalMallSku(Integer goodsId, String goodsName, String attribute, Double price, Integer stock, String updateTime) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.attribute = attribute;
        this.price = price;
        this.stock = stock;
        this.updateTime = updateTime;
    }
}