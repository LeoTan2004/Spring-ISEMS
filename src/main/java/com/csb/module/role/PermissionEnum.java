package com.csb.module.role;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum PermissionEnum {

    PERMISSION_MANAGE(1 << 11 | 1 << 10, "del,add,mod monitor and member,read of course"),
    PERMISSION_MANAGE_MONITOR(1 << 11, "del,add,mod monitor,read of course"),
    PERMISSION_MANAGE_MEMBER(1 << 10, "del,add,mod member,read of course"),
    //===============================
    PERMISSION_READ(1 << 2, "read the monitors and members in a team"),
    PERMISSION_READ_MONITOR(1 << 2 | 1 << 1, "just read the monitor in a team"),
    PERMISSION_READ_MEMBER(1 << 1, "just read the members in a team"),
    //===============================
    PERMISSION_NOTHING(0, "you can do nothing");
    @EnumValue
    private final int code;
    private final String description;


    public String getDescription() {
        return description;
    }

    PermissionEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }
}
