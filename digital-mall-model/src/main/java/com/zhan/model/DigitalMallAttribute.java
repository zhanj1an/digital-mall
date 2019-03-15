package com.zhan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DigitalMallAttribute {
    private Integer id;

    private String name;

    private Integer goodsId;

    private String updateTime;
}