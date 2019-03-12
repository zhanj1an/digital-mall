package com.zhan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DigitalMallGoodsAttribute {
    private String attributeName;
    private List<String> attributeValues;
}
