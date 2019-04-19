package com.zhan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DigitalMallCartInfo {
    private List<DigitalMallCartGoods> goodsList;
    private int totalNum;
    private double totalPrice;
}
