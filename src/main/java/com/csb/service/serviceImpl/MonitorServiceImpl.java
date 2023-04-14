package com.csb.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csb.module.authority.UserDO;
import com.csb.module.monitor.MonitorDO;
import com.csb.module.monitor.MonitorMapper;
import com.csb.module.team.TeamDO;
import com.csb.service.MonitorService;
import com.csb.utils.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorServiceImpl extends ServiceImpl<MonitorMapper, MonitorDO> implements MonitorService {

    @Autowired
    private MonitorMapper monitorMapper;

    @Override
    public List<MonitorDO> listByTeam(TeamDO teamDO, Long offset) {
        if (Assert.isNull(teamDO, offset)) {
            return null;
        }
        return listByTeam(teamDO.getTid(), offset);
    }

    @Override
    public List<MonitorDO> listByTeam(Long tid, Long offset) {
        if (Assert.isNull(tid, offset)) {
            return null;
        }
        return monitorMapper.listByTeam(tid, offset);
    }

    @Override
    public List<MonitorDO> listByUser(UserDO userDO, Long offset) {
        if (Assert.isNull(userDO, offset)) {
            return null;
        }
        return listByUser(userDO.getUid(), offset);
    }

    @Override
    public List<MonitorDO> listByUser(Long uid, Long offset) {
        if (Assert.isNull(uid, offset)) {
            return null;
        }
        return monitorMapper.listByUser(uid, offset);
    }

    @Override
    public Boolean linkWithTeam(Long mid, Long tid, Boolean isLink) {
        if (Assert.isNull(mid, tid, isLink)) {
            return null;
        }
        if (isLink) {
            return 1 == monitorMapper.linkWithTeam(mid, tid);
        } else {
            return 1 == monitorMapper.disLinkWithTeam(mid, tid);
        }
    }

    @Override
    public Boolean linkWithTeam(MonitorDO monitorDO, TeamDO teamDO, Boolean isLink) {
        if (Assert.isNull(monitorDO, teamDO, isLink)) {
            return null;
        }
        return linkWithTeam(monitorDO.getMid(), teamDO.getTid(), isLink);
    }

}
