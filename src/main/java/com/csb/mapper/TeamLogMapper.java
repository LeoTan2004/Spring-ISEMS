package com.csb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csb.pojo.TeamLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

/**
 * @author Leo
 * @description 针对表【t_team_log】的数据库操作Mapper
 * @createDate 2023-03-16 10:10:43
 * @Entity pojo.ExampleLog
 */

@Mapper
public interface TeamLogMapper extends BaseMapper<TeamLog> {

    List<TeamLog> getByMid(@Param("tid") long teamId, @Param("mid") long monitorId, @Param("offset")long offset);

    List<TeamLog> getByTime(@Param("tid") long teamId, @Param("sTime") Date startTime, @Param("eTime") Date endTime, @Param("offset")long offset);

    List<TeamLog> getByLevel(@Param("tid") long teamId, @Param("level") int level,@Param("offset")long offset);

    List<TeamLog> getByMsg(@Param("tid") long teamId, @Param("msg") String message,@Param("offset")long offset);

    void createTable(@Param("tid") long teamId);//动态生成表,这里有可能sql注入

    void dropTable(@Param("tid") long teamId);

    int insert(TeamLog entity);

}




