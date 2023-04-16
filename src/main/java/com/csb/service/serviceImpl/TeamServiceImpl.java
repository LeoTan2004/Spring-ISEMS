package com.csb.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csb.dto.TeamDTO;
import com.csb.exception.IllegalParamException;
import com.csb.module.authority.UserDO;
import com.csb.module.authority.UserMapper;
import com.csb.module.monitor.MonitorDO;
import com.csb.module.role.RoleDO;
import com.csb.module.team.TeamDO;
import com.csb.module.team.TeamMapper;
import com.csb.service.RoleService;
import com.csb.service.TeamService;
import com.csb.utils.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, TeamDO> implements TeamService {
    @Autowired
    private TeamMapper teamMapper;


    @Override
    public Boolean createTeam(TeamDTO param) {
        TeamDO teamDO = null;
        try {
            teamDO = param.toTeamDO();
        } catch (IllegalParamException e) {
            return null;
        }
        return 1 == teamMapper.insert(teamDO);
    }

    @Override
    public Boolean deleteTeam(TeamDO teamDO) {
        if (Assert.isNull(teamDO)) return null;
        return deleteTeam(teamDO.getTid());
    }

    @Override
    public Boolean deleteTeam(Long tid) {
        if (Assert.isNull(tid)) return null;
        if (1 == teamMapper.deleteById(tid)) {
            roleService.deleteAllWithTeam(tid);
            return true;
        }
        return false;
    }

    @Override
    public Boolean changeAdmin(TeamDO teamDO, UserDO userDO) {
        if (Assert.isNull(teamDO, userDO)) return null;
        return changeAdmin(teamDO.getTid(), userDO.getUid());
    }

    @Override
    public Boolean changeAdmin(Long tid, Long uid) {
        if (Assert.isNull(tid, uid)) return null;
        return 1 == teamMapper.setAdmin(tid, uid);
    }

    @Override
    public List<TeamDO> listByAdmin(UserDO admin, Long offset) {
        if (Assert.isNull(admin, offset)) return null;
        return listByAdmin(admin.getUid(), offset);
    }

    @Override
    public List<TeamDO> listByAdmin(Long adminId, Long offset) {
        if (Assert.isNull(adminId, offset)) return null;
        return teamMapper.listByAdmin(adminId, offset);
    }

    @Override
    public List<TeamDO> listByUser(UserDO userDO, Long offset) {
        if (Assert.isNull(userDO, offset)) return null;
        return listByUser(userDO.getUid(), offset);
    }

    @Override
    public List<TeamDO> listByUser(Long uid, Long offset) {
        if (Assert.isNull(uid, offset)) return null;
        return teamMapper.listByUser(uid, offset);
    }

    @Override
    public TeamDO getByMonitor(MonitorDO monitorDO) {
        if (Assert.isNull(monitorDO)) return null;
        return getByMonitor(monitorDO.getMid());
    }

    @Override
    public TeamDO getByMonitor(Long mid) {
        if (Assert.isNull(mid)) return null;
        return teamMapper.getByMonitor(mid);
    }

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleService roleService;


    @Override
    public Boolean applyToTeam(Long uid, Long tid) {
        if (Assert.isNull(uid, tid)) return null;
        RoleDO roleDO = roleService.getByTeamAndRoleName(tid, RoleDO.APPLICANT);
        return 1 == userMapper.linkWithRole(uid, roleDO.getRid());
    }

    @Override
    public Boolean quitTeam(Long uid, Long tid) {
        if (Assert.isNull(uid, tid)) return null;
        return roleService.deleteByUserAndTeam(uid, tid);
    }


    @Override
    public boolean save(TeamDO entity) {
        if (super.save(entity)) {
            roleService.initForTeam(entity.getTid());
            return true;
        }
        return false;
    }
}
