package com.zhan.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DigitalMallCartGoods {
    private String goodsName;
    private String goodsAttr;
    private String imgUrl;
    private int goodsNum;
    private double price;
}
