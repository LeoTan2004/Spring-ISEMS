package com.csb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csb.pojo.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Leo
 * @description 针对表【t_team】的数据库操作Mapper
 * @createDate 2023-03-16 10:10:43
 * @Entity pojo.Team
 */
@Mapper
public interface TeamMapper extends BaseMapper<Team> {
    List<Team> getByTeamAdmin(@Param("admin") long adminId, @Param("offset")long offset);

    List<Team> getByUser(@Param("uid") Long userId,@Param("offset")long offset);
}




