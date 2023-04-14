package com.csb.module.monitor;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface MonitorMapper extends BaseMapper<Monitor> {
    @Select({"select mid, name, location, rel_tid, status, insert_time, update_time from monitor_system.monitors where rel_tid=#{tid} limit #{offset},500;"})
    @Results(id = "BaseResultMap", value = {
            @Result(id = true, column = "mid", property = "mid", jdbcType = JdbcType.BIGINT),
            @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
            @Result(property = "location", column = "location", jdbcType = JdbcType.VARCHAR),
            @Result(property = "relTid", column = "rel_tid", jdbcType = JdbcType.BIGINT),
            @Result(property = "status", column = "status", jdbcType = JdbcType.INTEGER),

            @Result(property = "insertTime", column = "insert_time", jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "updateTime", column = "update_time", jdbcType = JdbcType.TIMESTAMP),
    })
    List<Monitor> getByTeam(@Param("tid") Long tid, @Param("offset") Long offset);

    @ResultMap("BaseResultMap")
    @Select({"select monitors.mid, monitors.name, monitors.location, monitors.rel_tid, monitors.status, monitors.insert_time, monitors.update_time " +
            "from monitor_system.monitors " +
            "where rel_tid in " +
            "(select rel_tid from roles where rid in " +
            "(select rel_rid from monitor_system.team_user where rel_uid = #{uid}))" +
            "limit #{offset},500;"})
    List<Monitor> getByUser(@Param("uid") Long uid, @Param("offset") Long offset);

    @Update("update monitor_system.monitors set rel_tid=#{tid} where mid = #{mid};")
    int linkWithTeam(@Param("mid") Long mid, @Param("tid") Long tid);

    @Update("update monitor_system.monitors set rel_tid=null where mid = #{mid} and rel_tid=#{tid};")
    int disLinkWithTeam(@Param("mid") Long mid, @Param("tid") Long tid);

}
