package com.csb.module.team;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum TeamStatusEnum {
    TEAM_STATUS_OPEN(1,"开放"),
    TEAM_STATUS_CLOSE(0,"封闭");

    @EnumValue
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    TeamStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    private String description;

}
