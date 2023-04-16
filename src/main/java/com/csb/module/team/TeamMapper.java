package com.csb.module.team;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TeamMapper extends BaseMapper<TeamDO> {
    @Select({"select tid, admin, status, team_name, description, team.insert_time, team.update_time " +
            "from monitor_system.team,monitor_system.team_user,monitor_system.role " +
            "where tid= monitor_system.role.rel_tid and rel_uid=#{uid} limit #{offset},500;"})
    List<TeamDO> listByUser(@Param("uid") Long uid, @Param("offset") Long offset);

    @Select("select tid, admin, status, team_name, description, team.insert_time, team.update_time " +
            " from monitor_system.team " +
            "where team.admin = #{admin} limit #{offset},500;")
    List<TeamDO> listByAdmin(@Param("admin") Long admin, @Param("offset") Long offset);

    @Select("select tid, admin, team.status, team_name, description, team.insert_time, team.update_time " +
            "from monitor_system.team,monitor_system.monitor where monitor.rel_tid=tid and mid = #{mid};")
    TeamDO getByMonitor(@Param("mid") Long mid);

    @Update("update monitor_system.team set admin = #{admin} where tid = #{tid};")
    Integer setAdmin(@Param("tid") Long tid, @Param("uid") Long admin);

}
