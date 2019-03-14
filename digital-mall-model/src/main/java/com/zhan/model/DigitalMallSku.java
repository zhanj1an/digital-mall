package com.zhan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DigitalMallSku {
    private Integer id;

    private Integer goodsId;

    private String goodsName;

    private String attribute;

    private Double oldPrice;

    private Double price;

    private Integer stock;

    private Integer isDelete;

    private String updateTime;

    private Integer pageNum = 1;

    private Integer pageSize = 10;

}