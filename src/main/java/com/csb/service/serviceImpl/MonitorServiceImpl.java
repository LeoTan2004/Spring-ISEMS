package com.csb.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csb.module.authority.User;
import com.csb.module.monitor.Monitor;
import com.csb.module.monitor.MonitorMapper;
import com.csb.module.team.Team;
import com.csb.service.MonitorService;
import com.csb.utils.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorServiceImpl extends ServiceImpl<MonitorMapper, Monitor> implements MonitorService {

    @Autowired
    private MonitorMapper monitorMapper;

    @Override
    public List<Monitor> getByTeam(Team team, Long offset) {
        if (Assert.isNull(team, offset)) {
            return null;
        }
        return getByTeam(team.getTid(), offset);
    }

    @Override
    public List<Monitor> getByTeam(Long tid, Long offset) {
        if (Assert.isNull(tid, offset)) {
            return null;
        }
        return monitorMapper.getByTeam(tid, offset);
    }

    @Override
    public List<Monitor> getByUser(User user, Long offset) {
        if (Assert.isNull(user, offset)) {
            return null;
        }
        return getByUser(user.getUid(), offset);
    }

    @Override
    public List<Monitor> getByUser(Long uid, Long offset) {
        if (Assert.isNull(uid, offset)) {
            return null;
        }
        return monitorMapper.getByUser(uid, offset);
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
    public Boolean linkWithTeam(Monitor monitor, Team team, Boolean isLink) {
        if (Assert.isNull(monitor, team, isLink)) {
            return null;
        }
        return linkWithTeam(monitor.getMid(), team.getTid(), isLink);
    }

}
