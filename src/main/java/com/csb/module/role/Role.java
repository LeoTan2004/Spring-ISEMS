package com.csb.module.role;

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
@TableName("roles")
public class Role {
    public static final String MEMBER = "默认成员";
    public static final String APPLICANT = "默认访客";

    @TableId(type = IdType.ASSIGN_ID)
    private Long rid;
    private Long relTid;
    private String name;
    private Permission permissions;
    @TableField(fill = FieldFill.INSERT)
    private Timestamp insertTime;
    @TableField(fill = FieldFill.UPDATE)
    private Timestamp updateTime;

    public static Role getDefaultMemberRole(Long relTid) {
        return new Role(null, relTid, MEMBER, Permission.PERMISSION_READ);
    }

    public static Role getDefaultApplicantRole(Long relTid) {
        return new Role(null, relTid, APPLICANT, Permission.PERMISSION_NOTHING);
    }

    public Role(Long rid, Long relTid, String name, Permission permissions) {
        this.rid = rid;
        this.relTid = relTid;
        this.name = name;
        this.permissions = permissions;
    }
}
