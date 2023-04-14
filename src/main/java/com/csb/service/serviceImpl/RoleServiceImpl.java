package com.csb.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csb.module.authority.User;
import com.csb.module.authority.UserMapper;
import com.csb.module.role.Permission;
import com.csb.module.role.Role;
import com.csb.module.role.RoleMapper;
import com.csb.module.team.Team;
import com.csb.service.RoleService;
import com.csb.utils.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Role> getByUser(User user, Long offset) {
        if (Assert.isNull(user, offset)) return null;
        return getByUser(user.getUid(), offset);
    }

    @Override
    public List<Role> getByUser(Long uid, Long offset) {
        if (Assert.isNull(uid, offset)) return null;
        return roleMapper.getByUser(uid, offset);
    }

    @Override
    public List<Role> getByTeam(Team team, Long offset) {
        if (Assert.isNull(team, offset)) return null;
        return getByTeam(team.getTid(), offset);
    }

    @Override
    public List<Role> getByTeam(Long tid, Long offset) {
        if (Assert.isNull(tid, offset)) return null;
        return roleMapper.getByTeam(tid, offset);
    }

    @Override
    public Boolean setPerMission(Long rid, Permission permission) {
        if (Assert.isNull(rid)) return null;
        return setPerMission(roleMapper.selectById(rid), permission);
    }

    @Override
    public Boolean setPerMission(Role role, Permission permission) {
        if (Assert.isNull(role)) return null;
        if (Assert.isNull(permission)) permission = Permission.PERMISSION_NOTHING;
        role.setPermissions(permission);
        return 1 == roleMapper.updateById(role);
    }

    @Override
    public Boolean deleteAllWithTeam(Long tid) {
        return roleMapper.deleteByRelTid(tid) > 0;
    }

    @Override
    public Role getByTeamAndRoleName(Long tid, String name) {
        if (Assert.isNull(tid,name))return null;
        return roleMapper.getByTeamAndName(tid,name);
    }

    @Override
    public Role getByTeamAndRoleName(Team team, String name) {
        if (Assert.isNull(team,name))return null;
        return getByTeamAndRoleName(team.getTid(),name);
    }

    @Override
    public Boolean linkedWithRole(Long uid, Long rid) {
        if (Assert.isNull(uid,rid))return null;
        return 1 == userMapper.linkWithRole(uid, rid);
    }

    @Override
    public Boolean linkedWithRole(User user, Role role) {
        if (Assert.isNull(user,role))return null;
        return linkedWithRole(user.getUid(),role.getRid());
    }
}
