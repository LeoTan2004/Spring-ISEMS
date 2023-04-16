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
public class RoleDO {
    public static final String MEMBER = "默认成员";
    public static final String APPLICANT = "默认访客";

    @TableId(type = IdType.ASSIGN_ID)
    private Long rid;
    private Long relTid;
    private String name;
    private PermissionEnum permissions;
    @TableField(fill = FieldFill.INSERT)
    private Timestamp insertTime;
    @TableField(fill = FieldFill.UPDATE)
    private Timestamp updateTime;

    public static RoleDO getDefaultMemberRole(Long relTid) {
        return new RoleDO(null, relTid, MEMBER, PermissionEnum.PERMISSION_READ);
    }

    public static RoleDO getDefaultApplicantRole(Long relTid) {
        return new RoleDO(null, relTid, APPLICANT, PermissionEnum.PERMISSION_NOTHING);
    }

    public RoleDO(Long rid, Long relTid, String name, PermissionEnum permissions) {
        this.rid = rid;
        this.relTid = relTid;
        this.name = name;
        this.permissions = permissions;
    }

    public boolean checkPermission(PermissionEnum permissions) {
        if (permissions == null )return false;
        return this.permissions.checkpermission(permissions);
    }
}
