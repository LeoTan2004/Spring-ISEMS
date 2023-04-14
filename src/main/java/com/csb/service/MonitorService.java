package com.csb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csb.module.authority.UserDO;
import com.csb.module.monitor.MonitorDO;
import com.csb.module.team.TeamDO;

import java.util.List;

public interface MonitorService extends IService<MonitorDO> {
    List<MonitorDO> listByTeam(TeamDO teamDO, Long offset);

    List<MonitorDO> listByTeam(Long tid, Long offset);

    List<MonitorDO> listByUser(UserDO userDO, Long offset);

    List<MonitorDO> listByUser(Long uid, Long offset);

    Boolean linkWithTeam(Long mid, Long tid, Boolean isLink);

    Boolean linkWithTeam(MonitorDO monitorDO, TeamDO teamDO, Boolean isLink);

}
