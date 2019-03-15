package com.zhan.model;

import lombok.*;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DigitalMallAttributeValue {
    private Integer id;

    private String attrValue;

    private Integer attrId;

    private Integer goodsId;

    private String updateTime;
}