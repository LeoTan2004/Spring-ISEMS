package com.csb.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.csb.pojo.TeamUsers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Leo
 * @description 针对表【t_team_users】的数据库操作Mapper
 * @createDate 2023-03-16 10:10:43
 * @Entity pojo.TeamUsers
 */
@Mapper
public interface TeamUsersMapper extends BaseMapper<TeamUsers> {
    List<TeamUsers> getAllByUidAndStatus(@Param("uid") long userId, @Param("status") Integer status, @Param("offset") long offset);
    List<TeamUsers> getAllByUid(@Param("uid") long userId, @Param("offset") long offset);

    List<TeamUsers> getAllByTid(@Param("tid") long teamId, @Param("offset") long offset);
    List<TeamUsers> getAllByTidAndStatus(@Param("tid") long teamId, @Param("status") Integer status, @Param("offset") long offset);

    TeamUsers getByRelTidAndRelUidTeamUsers(@Param("tid") long tid, @Param("uid") long uid);

    int deleteOb(TeamUsers teamUsers);

}




