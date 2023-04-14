package com.csb.module.authority;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@TableName("users")
public class UserDO {
    @TableId(type = IdType.ASSIGN_ID)
    private Long uid;
    private String username;
    private Long phone;
    private String email;
    private Date lastLoginTime;
    @TableField(fill = FieldFill.INSERT)
    private Timestamp insertTime;
    @TableField(fill = FieldFill.UPDATE)
    private Timestamp updateTime;
}
