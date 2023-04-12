package com.csb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csb.pojo.UserMonitor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Leo
 * @description 针对表【t_user_monitor】的数据库操作Mapper
 * @createDate 2023-03-16 10:10:43
 * @Entity pojo.UserMonitor
 */
@Mapper
public interface UserMonitorMapper extends BaseMapper<UserMonitor> {
    List<UserMonitor> getByUid(@Param("uid") long userId, @Param("offset")long offset);

    List<UserMonitor> getByMid(@Param("mid") long monitorId, @Param("offset")long offset);
    int deleteByRelMidAndRelUid(@Param("mid") long monitorId,@Param("uid") long userId);
    UserMonitor getByRelMidAndRelUid(@Param("mid") long monitorId,@Param("uid") long userId);
}




