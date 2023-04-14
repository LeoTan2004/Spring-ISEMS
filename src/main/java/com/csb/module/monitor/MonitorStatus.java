package com.csb.module.monitor;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum MonitorStatus {
    MONITOR_STATUS_ONLINE(1,"正常"),
    MONITOR_STATUS_OFFLINE(0,"离线"),
    MONITOR_STATUS_CRUSH(1<<31,"设备内部发生异常,无法正常通信"),
    MONITOR_STATUS_MSG(1<<1,"设备有消息"),
    MONITOR_STATUS_ERROR(1<<2,"设备异常报告");
    @EnumValue
    private final Integer code;
    private final String description;

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    MonitorStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
