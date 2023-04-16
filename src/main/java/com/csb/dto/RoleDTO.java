package com.csb.dto;

import com.csb.exception.IllegalParamException;
import com.csb.module.role.PermissionEnum;
import com.csb.module.role.RoleDO;
import com.csb.utils.Assert;

public record RoleDTO(Long rid, Long tid, String name, PermissionEnum permissionEnum) {

    /**
     * for insert
     */
    public RoleDO toRoleDO() throws IllegalParamException {
        if (illegalForInsert()) {
            throw new IllegalParamException("角色参数不足");
        }
        return this.toRoleDo(null);
    }

    /**
     * for update
     */
    public RoleDO toRoleDO(Long rid) throws IllegalParamException {
        if (illegalForUpdate()) throw new IllegalParamException("角色参数不足");
        return toRoleDo(rid);

    }

    private RoleDO toRoleDo(Long rid) {
        PermissionEnum p = this.permissionEnum == null ? PermissionEnum.PERMISSION_NOTHING : this.permissionEnum;
        return new RoleDO(rid, this.tid, name, p);
    }

    public boolean illegalForUpdate() {
        return Assert.isNull(rid,name,tid);
    }

    public boolean illegalForInsert() {
        return (Assert.isNull(tid, name));
    }

}
