package com.csb.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csb.module.authority.UserDO;
import com.csb.module.authority.UserMapper;
import com.csb.module.role.PermissionEnum;
import com.csb.module.role.RoleDO;
import com.csb.module.role.RoleMapper;
import com.csb.module.team.TeamDO;
import com.csb.service.RoleService;
import com.csb.utils.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleDO> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<RoleDO> listByUser(UserDO userDO, Long offset) {
        if (Assert.isNull(userDO, offset)) return null;
        return listByUser(userDO.getUid(), offset);
    }

    @Override
    public List<RoleDO> listByUser(Long uid, Long offset) {
        if (Assert.isNull(uid, offset)) return null;
        return roleMapper.listByUser(uid, offset);
    }

    @Override
    public List<RoleDO> listByTeam(TeamDO teamDO, Long offset) {
        if (Assert.isNull(teamDO, offset)) return null;
        return listByTeam(teamDO.getTid(), offset);
    }

    @Override
    public List<RoleDO> listByTeam(Long tid, Long offset) {
        if (Assert.isNull(tid, offset)) return null;
        return roleMapper.listByTeam(tid, offset);
    }

    @Override
    public Boolean setPerMission(Long rid, PermissionEnum permissionEnum) {
        if (Assert.isNull(rid)) return null;
        return setPerMission(roleMapper.selectById(rid), permissionEnum);
    }

    @Override
    public Boolean setPerMission(RoleDO roleDO, PermissionEnum permissionEnum) {
        if (Assert.isNull(roleDO)) return null;
        if (Assert.isNull(permissionEnum)) permissionEnum = PermissionEnum.PERMISSION_NOTHING;
        roleDO.setPermissions(permissionEnum);
        return 1 == roleMapper.updateById(roleDO);
    }

    @Override
    public Boolean deleteAllWithTeam(Long tid) {
        return roleMapper.deleteByRelTid(tid) > 0;
    }

    @Override
    public RoleDO getByTeamAndRoleName(Long tid, String name) {
        if (Assert.isNull(tid, name)) return null;
        return roleMapper.getByTeamAndName(tid, name);
    }

    @Override
    public RoleDO getByTeamAndRoleName(TeamDO teamDO, String name) {
        if (Assert.isNull(teamDO, name)) return null;
        return getByTeamAndRoleName(teamDO.getTid(), name);
    }

    @Override
    public Boolean linkedWithRole(Long uid, Long rid) {
        if (Assert.isNull(uid, rid)) return null;
        return 1 == userMapper.linkWithRole(uid, rid);
    }

    @Override
    public Boolean linkedWithRole(UserDO userDO, RoleDO roleDO) {
        if (Assert.isNull(userDO, roleDO)) return null;
        return linkedWithRole(userDO.getUid(), roleDO.getRid());
    }

    @Override
    public Boolean initForTeam(TeamDO teamDO) {
        if (Assert.isNull(teamDO)) return null;
        return initForTeam(teamDO.getTid());
    }

    @Override
    public Boolean initForTeam(Long tid) {
        if (Assert.isNull(tid)) return null;
        if (Assert.isNull(roleMapper.getByTeamAndName(tid, RoleDO.APPLICANT)))
            roleMapper.insert(RoleDO.getDefaultApplicantRole(tid));
        if (Assert.isNull(roleMapper.getByTeamAndName(tid, RoleDO.MEMBER)))
            roleMapper.insert(RoleDO.getDefaultMemberRole(tid));
        return true;
    }

    @Override
    public Boolean deleteByUserAndTeam(Long uid, Long tid) {
        if (Assert.isNull(uid, tid)) return null;
        RoleDO roleDO = roleMapper.getByUserAndTeam(uid, tid);
        if (null == roleDO) return true;
        return 0 < userMapper.disLinkWithRole(uid, roleDO.getRid());
    }

    @Override
    public Boolean deleteByUserAndTeam(UserDO userDo, TeamDO teamDO) {
        if (Assert.isNull(userDo, teamDO)) return null;
        return deleteByUserAndTeam(userDo.getUid(), teamDO.getTid());
    }

    @Override
    public boolean save(RoleDO entity) {
        if (null == roleMapper.getByTeamAndName(entity.getRelTid(), entity.getName()))
            return super.save(entity);
        return false;
    }
}
