package com.csb.module.account;


import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Gender {
    GENDER_MAN(1),GENDER_WOMAN(0);
    @EnumValue
    private final int code;
}
