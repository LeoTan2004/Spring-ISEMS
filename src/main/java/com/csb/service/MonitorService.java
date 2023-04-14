package com.csb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csb.module.authority.User;
import com.csb.module.monitor.Monitor;
import com.csb.module.team.Team;

import java.util.List;

public interface MonitorService extends IService<Monitor> {
    List<Monitor> getByTeam(Team team, Long offset);

    List<Monitor> getByTeam(Long tid, Long offset);

    List<Monitor> getByUser(User user, Long offset);

    List<Monitor> getByUser(Long uid, Long offset);

    Boolean linkWithTeam(Long mid, Long tid, Boolean isLink);

    Boolean linkWithTeam(Monitor monitor, Team team, Boolean isLink);

}
