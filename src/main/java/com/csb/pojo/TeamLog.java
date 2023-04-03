package com.csb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


//@TableName(value ="t_team_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamLog implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String lid;

    @TableField(exist = false)
    private Long tid;

    /**
     * 
     */
    private Long relMid;

    /**
     * 
     */
    private Date time;

    /**
     * 
     */
    private Integer level;

    /**
     * 
     */
    private String msg;

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
        TeamLog other = (TeamLog) that;
        return (this.getLid() == null ? other.getLid() == null : this.getLid().equals(other.getLid()))
            && (this.getRelMid() == null ? other.getRelMid() == null : this.getRelMid().equals(other.getRelMid()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getMsg() == null ? other.getMsg() == null : this.getMsg().equals(other.getMsg()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLid() == null) ? 0 : getLid().hashCode());
        result = prime * result + ((getRelMid() == null) ? 0 : getRelMid().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getMsg() == null) ? 0 : getMsg().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", lid=").append(lid);
        sb.append(", relMid=").append(relMid);
        sb.append(", time=").append(time);
        sb.append(", level=").append(level);
        sb.append(", msg=").append(msg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}