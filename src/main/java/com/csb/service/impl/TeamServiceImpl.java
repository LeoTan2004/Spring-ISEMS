package com.csb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csb.mapper.TeamMapper;
import com.csb.pojo.Team;
import com.csb.pojo.User;
import com.csb.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Leo
 * @description 针对表【t_team】的数据库操作Service实现
 * @createDate 2023-03-16 10:10:43
 */
@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team>
        implements TeamService {
    @Autowired
    private TeamMapper teamMapper;

    @Override
    public Team getById(Long id) {
        return this.getById(id);
    }

    @Override
    public List<Team> getByAdmin(User admin, long offset) {
        return teamMapper.getByTeamAdmin(admin.getUid(), offset);
    }

    @Override
    public boolean delTeam(Team team) {
        return this.removeById(team.getTid());
    }

    @Override
    public boolean setTeamName(Team team, String name) {
        Team team1 = teamMapper.selectById(team.getTid());
        if (team1.getTeamname().equals(name)) {
            return true;
        }
        team1.setTeamname(name);
        return teamMapper.updateById(team1) == 1;
    }

    @Override
    public boolean setTeamAdmin(Team team, User admin) {
        Team team1 = teamMapper.selectById(team.getTid());
        if (team1.getTeamAdmin().equals(admin.getUid())) {
            return true;
        }
        team1.setTeamAdmin(admin.getUid());
        return teamMapper.updateById(team1) == 1;
    }

    public boolean addTeam(Team team) {
        Long tid = team.getTid();
        team.setLogTablename(tid.toString());
        return teamMapper.insert(team) == 1;
    }
}




