package com.zhan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DigitalMallGoodsSynopsis {
    private int goodsId;
    private String goodsName;
    private String category;
    private double lowestPrice;
    private double highestPrice;
    private String goodsIntroduce;
    private List<String> imgUrlList;
    private List<String> desUrlList;
}
