package com.csb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csb.dto.TeamCreateParam;
import com.csb.module.authority.User;
import com.csb.module.monitor.Monitor;
import com.csb.module.team.Team;

import java.util.List;

public interface TeamService extends IService<Team> {
    Boolean createTeam(TeamCreateParam param);

    Boolean deleteTeam(Team team);

    Boolean deleteTeam(Long tid);

    Boolean changeAdmin(Team team, User user);

    Boolean changeAdmin(Long tid, Long uid);

    List<Team> getByAdmin(User admin, Long offset);

    List<Team> getByAdmin(Long adminId, Long offset);

    List<Team> getByUser(User user, Long offset);

    List<Team> getByUser(Long uid, Long offset);

    Team getByMonitor(Monitor monitor);

    Team getByMonitor(Long mid);

    Boolean applyToTeam(Long uid, Long tid);

    Boolean quitTeam(Long uid, Long tid);
}
