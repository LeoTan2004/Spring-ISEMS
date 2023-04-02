package com.csb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csb.mapper.RoleMapper;
import com.csb.pojo.Role;
import com.csb.pojo.Team;
import com.csb.pojo.User;
import com.csb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Leo
 * @description 针对表【t_role】的数据库操作Service实现
 * @createDate 2023-03-16 10:10:43
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public boolean addRole(Team team,Role role){
        Long tid = team.getTid();
        role.setRelTid(tid);
        return roleMapper.insert(role) == 1;
    }


    @Override
    public Role getById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean delRole(Role role) {
        return this.removeById(role.getRid());
    }

    @Override
    public List<Role> getRoleByTeam(Team team, long offset) {
        return roleMapper.getByTid(team.getTid(), offset);
    }

    @Override
    public List<Role> getRoleByUser(User user, long offset) {
        return roleMapper.getByUser(user.getUid(), offset);
    }

    @Override
    public Role getByTeamAndName(Team team, String name) {
        return roleMapper.getByTidAndRoleName(team.getTid(), name);
    }
}




