package com.zhan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DigitalMallGoodsInfo {
    private DigitalMallGoodsSynopsis goodsSynopsis;
    private List<DigitalMallGoodsAttribute> attributeList;
}
