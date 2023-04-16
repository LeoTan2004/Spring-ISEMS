package com.csb.module.monitor;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MonitorMapper extends BaseMapper<MonitorDO> {
    @Select({"select mid, name, location, rel_tid, status, insert_time, update_time " +
            "from monitor_system.monitor where rel_tid=#{tid} limit #{offset},500;"})
    List<MonitorDO> listByTeam(@Param("tid") Long tid, @Param("offset") Long offset);

    @Select({"select monitor.mid, monitor.name, monitor.location, monitor.rel_tid, monitor.status, monitor.insert_time, monitor.update_time " +
            "from monitor_system.monitor " +
            "where rel_tid in " +
            "(select rel_tid from role where rid in " +
            "(select rel_rid from monitor_system.team_user where rel_uid = #{uid}))" +
            "limit #{offset},500;"})
    List<MonitorDO> listByUser(@Param("uid") Long uid, @Param("offset") Long offset);

    @Update("update monitor_system.monitor set rel_tid=#{tid} where mid = #{mid};")
    int linkWithTeam(@Param("mid") Long mid, @Param("tid") Long tid);

    @Update("update monitor_system.monitor set rel_tid=null where mid = #{mid} and rel_tid=#{tid};")
    int disLinkWithTeam(@Param("mid") Long mid, @Param("tid") Long tid);

}
