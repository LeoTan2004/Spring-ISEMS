package com.csb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csb.pojo.Monitor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Leo
 * @description 针对表【t_monitor】的数据库操作Mapper
 * @createDate 2023-03-16 10:10:43
 * @Entity pojo.Monitor
 */
@Mapper
public interface MonitorMapper extends BaseMapper<Monitor> {
    List<Monitor> getByTid(@Param("tid") long teamId,@Param("offset")long offset);

    List<Monitor> getByAdmin(@Param("adminId") long adminId,@Param("offset")long offset);

    Monitor getByTidAndName(@Param("tid") long teamId, @Param("name") String monitorName);

    List<Monitor> getByUser(@Param("uid") Long userId,@Param("offset")long offset);
}




