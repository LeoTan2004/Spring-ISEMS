package com.csb.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csb.dto.TeamCreateParam;
import com.csb.module.authority.User;
import com.csb.module.authority.UserMapper;
import com.csb.module.monitor.Monitor;
import com.csb.module.role.Role;
import com.csb.module.role.RoleMapper;
import com.csb.module.team.Team;
import com.csb.module.team.TeamMapper;
import com.csb.module.team.TeamStatus;
import com.csb.service.TeamService;
import com.csb.utils.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team> implements TeamService {
    @Autowired
    private TeamMapper teamMapper;

    @Override
    public Boolean createTeam(TeamCreateParam param) {
        Team team = getTeamFromParam(param);
        return 1 == teamMapper.insert(team);
    }

    private Team getTeamFromParam(TeamCreateParam param) {
        String teamName, description;
        Long admin;
        if (Assert.isEnpty((teamName = param.getTeamName())) || Assert.isNull((admin = param.getAdmin()))) {
            return null;
        }
        description = param.getDescription();
        return new Team(admin, TeamStatus.TEAM_STATUS_OPEN, teamName, description);
    }

    @Override
    public Boolean deleteTeam(Team team) {
        if (Assert.isNull(team)) return null;
        return deleteTeam(team.getTid());
    }

    @Override
    public Boolean deleteTeam(Long tid) {
        if (Assert.isNull(tid)) return null;
        return 1 == teamMapper.deleteById(tid);
    }

    @Override
    public Boolean changeAdmin(Team team, User user) {
        if (Assert.isNull(team, user)) return null;
        return changeAdmin(team.getTid(), user.getUid());
    }

    @Override
    public Boolean changeAdmin(Long tid, Long uid) {
        if (Assert.isNull(tid, uid)) return null;
        return 1 == teamMapper.setAdmin(tid, uid);
    }

    @Override
    public List<Team> getByAdmin(User admin, Long offset) {
        if (Assert.isNull(admin, offset)) return null;
        return getByAdmin(admin.getUid(), offset);
    }

    @Override
    public List<Team> getByAdmin(Long adminId, Long offset) {
        if (Assert.isNull(adminId, offset)) return null;
        return teamMapper.getByAdmin(adminId, offset);
    }

    @Override
    public List<Team> getByUser(User user, Long offset) {
        if (Assert.isNull(user, offset)) return null;
        return getByUser(user.getUid(), offset);
    }

    @Override
    public List<Team> getByUser(Long uid, Long offset) {
        if (Assert.isNull(uid, offset)) return null;
        return teamMapper.getByUser(uid, offset);
    }

    @Override
    public Team getByMonitor(Monitor monitor) {
        if (Assert.isNull(monitor)) return null;
        return getByMonitor(monitor.getMid());
    }

    @Override
    public Team getByMonitor(Long mid) {
        if (Assert.isNull(mid)) return null;
        return teamMapper.getByMonitor(mid);
    }

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Boolean applyToTeam(Long uid, Long tid) {
        if (Assert.isNull(uid,tid)) return null;
        Role role = roleMapper.getByTeamAndName(tid, Role.APPLICANT);
        return 1 == userMapper.linkWithRole(uid, role.getRid());
    }

    @Override
    public Boolean quitTeam(Long uid, Long tid) {
        if (Assert.isNull(uid,tid)) return null;
        Role role = roleMapper.getByUserAndTeam(uid, tid);
        return 1 == userMapper.disLinkWithRole(uid, role.getRid());
    }
}
