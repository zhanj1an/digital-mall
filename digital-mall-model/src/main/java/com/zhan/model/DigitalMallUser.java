package com.zhan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DigitalMallUser {
    private Integer id;

    private String username;

    private String password;

    private Integer userType;
}