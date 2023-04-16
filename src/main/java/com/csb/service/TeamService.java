package com.csb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csb.dto.TeamDTO;
import com.csb.module.authority.UserDO;
import com.csb.module.monitor.MonitorDO;
import com.csb.module.team.TeamDO;

import java.util.List;

public interface TeamService extends IService<TeamDO> {
    Boolean createTeam(TeamDTO param);

    Boolean deleteTeam(TeamDO teamDO);

    Boolean deleteTeam(Long tid);

    Boolean changeAdmin(TeamDO teamDO, UserDO userDO);

    Boolean changeAdmin(Long tid, Long uid);

    List<TeamDO> listByAdmin(UserDO admin, Long offset);

    List<TeamDO> listByAdmin(Long adminId, Long offset);

    List<TeamDO> listByUser(UserDO userDO, Long offset);

    List<TeamDO> listByUser(Long uid, Long offset);

    TeamDO getByMonitor(MonitorDO monitorDO);

    TeamDO getByMonitor(Long mid);

    Boolean applyToTeam(Long uid, Long tid);

    Boolean quitTeam(Long uid, Long tid);
}
