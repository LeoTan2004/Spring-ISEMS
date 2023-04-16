package com.csb.module.monitor;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@TableName("monitor")
public class MonitorDO {
    @TableId(type = IdType.ASSIGN_ID)
    private Long mid;
    private String name;
    private String location;
    private Long relTid;
    private MonitorStatusEnum status;
    @TableField(fill = FieldFill.INSERT)
    private Timestamp insertTime;
    @TableField(fill = FieldFill.UPDATE)
    private Timestamp updateTime;
}
