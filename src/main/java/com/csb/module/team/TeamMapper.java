package com.csb.module.team;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TeamMapper extends BaseMapper<Team> {
    @Select({"select tid, admin, status, team_name, description, teams.insert_time, teams.update_time " +
            "from monitor_system.teams,monitor_system.team_user,monitor_system.roles " +
            "where tid=roles.rel_tid and rel_uid=#{uid} limit #{offset},500;"})
    List<Team> getByUser(@Param("uid") Long uid, @Param("offset") Long offset);
    @Select("select tid, admin, status, team_name, description, teams.insert_time, teams.update_time " +
            " from monitor_system.teams " +
            "where teams.admin = #{admin} limit #{offset},500;")
    List<Team> getByAdmin(@Param("admin") Long admin, @Param("offset") Long offset);

    @Select("select tid, admin, teams.status, team_name, description, teams.insert_time, teams.update_time from monitor_system.teams,monitor_system.monitors where monitors.rel_tid=tid and mid = #{mid};")
    Team getByMonitor(@Param("mid") Long mid);

    @Update("update monitor_system.teams set admin = #{admin} where tid = #{tid};")
    Integer setAdmin(@Param("tid")Long tid,@Param("uid")Long admin);

}
