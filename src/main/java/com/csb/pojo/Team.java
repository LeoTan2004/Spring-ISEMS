package com.csb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@TableName(value ="t_team")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long tid;

    /**
     * 
     */
    private String logTablename;

    /**
     * 
     */
    private String teamname;

    /**
     * 
     */
    private Long teamAdmin;

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
        Team other = (Team) that;
        return (this.getTid() == null ? other.getTid() == null : this.getTid().equals(other.getTid()))
            && (this.getLogTablename() == null ? other.getLogTablename() == null : this.getLogTablename().equals(other.getLogTablename()))
            && (this.getTeamname() == null ? other.getTeamname() == null : this.getTeamname().equals(other.getTeamname()))
            && (this.getTeamAdmin() == null ? other.getTeamAdmin() == null : this.getTeamAdmin().equals(other.getTeamAdmin()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTid() == null) ? 0 : getTid().hashCode());
        result = prime * result + ((getLogTablename() == null) ? 0 : getLogTablename().hashCode());
        result = prime * result + ((getTeamname() == null) ? 0 : getTeamname().hashCode());
        result = prime * result + ((getTeamAdmin() == null) ? 0 : getTeamAdmin().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tid=").append(tid);
        sb.append(", logTablename=").append(logTablename);
        sb.append(", teamname=").append(teamname);
        sb.append(", teamAdmin=").append(teamAdmin);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}