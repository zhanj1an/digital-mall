package com.zhan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DigitalMallOrder {
    private Integer id;

    private String account;

    private String username;

    private String telephone;

    private String address;

    private String remarks;

    private Double orderPrice;

    private Integer goodsNum;

    private Integer isPay;

    private Integer isSend;

    private String createTime;
}