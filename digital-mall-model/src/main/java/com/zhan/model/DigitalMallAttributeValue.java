package com.zhan.model;

import lombok.Data;

import java.util.Objects;

@Data
public class DigitalMallAttributeValue {
    private Integer id;

    private String attrValue;

    private Integer attrId;

    private Integer goodsId;

    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue == null ? null : attrValue.trim();
    }

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public DigitalMallAttributeValue(String attrValue, Integer attrId, Integer goodsId, String updateTime) {
        this.attrValue = attrValue;
        this.attrId = attrId;
        this.goodsId = goodsId;
        this.updateTime = updateTime;
    }

    public DigitalMallAttributeValue() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DigitalMallAttributeValue that = (DigitalMallAttributeValue) o;
        return attrValue.equals(that.attrValue) &&
                attrId.equals(that.attrId) &&
                goodsId.equals(that.goodsId) &&
                updateTime.equals(that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attrValue, attrId, goodsId, updateTime);
    }
}