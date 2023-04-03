package com.csb.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@TableName(value ="t_user_monitor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMonitor implements Serializable {
    /**
     * 
     */
    private Long relUid;

    /**
     * 
     */
    private Long relMid;

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
        UserMonitor other = (UserMonitor) that;
        return (this.getRelUid() == null ? other.getRelUid() == null : this.getRelUid().equals(other.getRelUid()))
            && (this.getRelMid() == null ? other.getRelMid() == null : this.getRelMid().equals(other.getRelMid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRelUid() == null) ? 0 : getRelUid().hashCode());
        result = prime * result + ((getRelMid() == null) ? 0 : getRelMid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", relUid=").append(relUid);
        sb.append(", relMid=").append(relMid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}