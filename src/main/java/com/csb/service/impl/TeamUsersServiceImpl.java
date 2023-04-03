package com.csb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csb.mapper.TeamUsersMapper;
import com.csb.dto.Status;
import com.csb.pojo.Team;
import com.csb.pojo.TeamUsers;
import com.csb.pojo.User;
import com.csb.service.TeamUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Leo
 * @description 针对表【t_team_users】的数据库操作Service实现
 * @createDate 2023-03-16 10:10:43
 */
@Service
public class TeamUsersServiceImpl extends ServiceImpl<TeamUsersMapper, TeamUsers>
        implements TeamUsersService {

    @Autowired
    private TeamUsersMapper mapper;


    @Override
    public boolean addTeamUser(Team team, User user, String description) {
        return mapper.insert(new TeamUsers(Status.IN_TEAM,new Date(), user.getUid(), description, team.getTid())) == 1;
    }

    @Override
    public boolean applyToTeam(Team team, User user, String description) {
        return mapper.insert(new TeamUsers(Status.APPLY,new Date(), user.getUid(), description, team.getTid())) == 1;
    }

    @Override
    public boolean delTeamUser(Team team, User user) {
        TeamUsers teamUsers = mapper.getByRelTidAndRelUidTeamUsers(team.getTid(), user.getUid());
        if (teamUsers == null) {
            return true;
        }
        return mapper.deleteOb(teamUsers) == 1;
    }

    @Override
    public List<TeamUsers> getByTeam(Team team, long offset) {
        return mapper.getAllByTid(team.getTid(), offset);
    }

    @Override
    public List<TeamUsers> getByUser(User user, long offset) {
        return mapper.getAllByUid(user.getUid(), offset);
    }

    @Override
    public TeamUsers getByTeamAndUser(User user, Team team) {
        return mapper.getByRelTidAndRelUidTeamUsers(team.getTid(),user.getUid());
    }
}




