package com.csb.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
 * @TableName t_role
 */
@TableName(value ="t_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    /**
     * 
     */
    @TableId
    private Long rid;

    /**
     * 
     */
    private Long relTid;

    /**
     * 
     */
    private String role;

    /**
     * 
     */
    private String permission;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Role other = (Role) that;
        return (this.getRid() == null ? other.getRid() == null : this.getRid().equals(other.getRid()))
            && (this.getRelTid() == null ? other.getRelTid() == null : this.getRelTid().equals(other.getRelTid()))
            && (this.getRole() == null ? other.getRole() == null : this.getRole().equals(other.getRole()))
            && (this.getPermission() == null ? other.getPermission() == null : this.getPermission().equals(other.getPermission()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRid() == null) ? 0 : getRid().hashCode());
        result = prime * result + ((getRelTid() == null) ? 0 : getRelTid().hashCode());
        result = prime * result + ((getRole() == null) ? 0 : getRole().hashCode());
        result = prime * result + ((getPermission() == null) ? 0 : getPermission().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rid=").append(rid);
        sb.append(", relTid=").append(relTid);
        sb.append(", role=").append(role);
        sb.append(", permission=").append(permission);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}