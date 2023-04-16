package com.csb.module.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<RoleDO> {
    @Select({"select rid, rel_tid, name, permissions, role.insert_time, role.update_time " +
            "from monitor_system.role,monitor_system.team_user " +
            "where team_user.rel_uid=#{uid} and team_user.rel_rid=role.rid limit #{offset},500;"})
    List<RoleDO> listByUser(@Param("uid") Long uid, @Param("offset") Long offset);

    @Select("select rid, rel_tid, name, permissions, role.insert_time, role.update_time " +
            "from monitor_system.role,monitor_system.team_user " +
            "where rel_tid = #{tid} " +
            "and rel_rid = rid " +
            "and rel_uid = #{uid}")
    RoleDO getByUserAndTeam(@Param("uid") Long uid, @Param("tid") Long tid);

    @Select("select rid, rel_tid, name, permissions, role.insert_time, role.update_time from role " +
            "where rel_tid=#{tid} limit #{offset},500;")
    List<RoleDO> listByTeam(@Param("tid") Long tid, @Param("offset") Long offset);

    @Select("select rid, rel_tid, name, permissions, role.insert_time, role.update_time from role " +
            "where rel_tid=#{tid} and name  = #{name} limit 1;")
    RoleDO getByTeamAndName(@Param("tid") Long tid, @Param("name") String name);

    @Delete("delete from monitor_system.role where rel_tid = #{tid};")
    Integer deleteByRelTid(@Param("tid") Long tid);

}
