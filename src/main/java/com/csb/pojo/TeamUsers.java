package com.csb.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@TableName(value ="t_team_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamUsers implements Serializable {
    private Integer status;
    /**
     * 
     */
    private Date time;

    /**
     * 
     */
    private Long relUid;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private Long relTid;

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
        TeamUsers other = (TeamUsers) that;
        return (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getRelUid() == null ? other.getRelUid() == null : this.getRelUid().equals(other.getRelUid()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getRelTid() == null ? other.getRelTid() == null : this.getRelTid().equals(other.getRelTid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getRelUid() == null) ? 0 : getRelUid().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getRelTid() == null) ? 0 : getRelTid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", time=").append(time);
        sb.append(", relUid=").append(relUid);
        sb.append(", description=").append(description);
        sb.append(", relTid=").append(relTid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}