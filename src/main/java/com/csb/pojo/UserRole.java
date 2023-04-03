package com.csb.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@TableName(value ="t_user_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole implements Serializable {
    /**
     * 
     */
    private Long relUserId;

    /**
     * 
     */
    private Long relRoleId;

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
        UserRole other = (UserRole) that;
        return (this.getRelUserId() == null ? other.getRelUserId() == null : this.getRelUserId().equals(other.getRelUserId()))
            && (this.getRelRoleId() == null ? other.getRelRoleId() == null : this.getRelRoleId().equals(other.getRelRoleId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRelUserId() == null) ? 0 : getRelUserId().hashCode());
        result = prime * result + ((getRelRoleId() == null) ? 0 : getRelRoleId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", relUserId=").append(relUserId);
        sb.append(", relRoleId=").append(relRoleId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}