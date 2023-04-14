package com.csb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 注册时需要的基本的数据集
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RegisterParam {

    private String username;
    private String password;
    private Long phone;
}
