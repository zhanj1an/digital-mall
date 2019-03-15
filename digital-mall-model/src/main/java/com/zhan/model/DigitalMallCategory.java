package com.zhan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DigitalMallCategory {
    private Integer id;

    private String name;

    private String updateTime;
}