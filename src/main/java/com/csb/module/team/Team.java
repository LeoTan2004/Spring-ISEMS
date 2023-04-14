package com.csb.module.team;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@TableName("teams")
public class Team {
    @TableId(type = IdType.ASSIGN_ID)
    private Long tid;
    private Long admin;
    private TeamStatus status;
    private String teamName;
    private String description;
    @TableField(fill = FieldFill.INSERT)
    private Timestamp insertTime;
    @TableField(fill = FieldFill.UPDATE)
    private Timestamp updateTime;

    public Team( Long admin, TeamStatus status, String teamName, String description) {
        this.admin = admin;
        this.status = status;
        this.teamName = teamName;
        this.description = description;
    }
}
