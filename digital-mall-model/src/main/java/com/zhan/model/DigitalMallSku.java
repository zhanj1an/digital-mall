package com.zhan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DigitalMallSku {
    private Integer id;

    private Integer goodsId;

    private String goodsName;

    private String attribute;

    private Double price;

    private Integer stock;

    private Integer isDelete;

    private String updateTime;

    private int pageNum = 1;

    private int pageSize = 10;

}