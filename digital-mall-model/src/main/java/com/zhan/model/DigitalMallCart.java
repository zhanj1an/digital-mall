package com.zhan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DigitalMallCart {
    private Integer id;

    private Integer userId;

    private Integer totalNum;

    private Double totalPrice;

    public DigitalMallCart(Integer userId, Integer totalNum, Double totalPrice) {
        this.userId = userId;
        this.totalNum = totalNum;
        this.totalPrice = totalPrice;
    }
}