package com.zhan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DigitalMallCategoryBrand {
    private Integer id;

    private Integer categoryId;

    private Integer brandId;

    private String updateTime;
}