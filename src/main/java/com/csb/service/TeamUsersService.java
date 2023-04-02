package com.csb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csb.pojo.Team;
import com.csb.pojo.TeamUsers;
import com.csb.pojo.User;

import java.util.List;
/**
* @author Leo
* @description 针对表【t_team_users】的数据库操作Service
* @createDate 2023-03-16 10:10:43
*/
public interface TeamUsersService extends IService<TeamUsers> {
    boolean addTeamUser(Team team, User user,String description);
    boolean applyToTeam(Team team, User user,String description);
    boolean delTeamUser(Team team, User user);
    List<TeamUsers> getByTeam(Team team,long offset);
    List<TeamUsers> getByUser(User user,long offset);
    TeamUsers getByTeamAndUser(User user,Team team);
}
