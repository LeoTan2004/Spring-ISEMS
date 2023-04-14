package com.csb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csb.module.authority.User;
import com.csb.module.role.Permission;
import com.csb.module.role.Role;
import com.csb.module.team.Team;

import java.util.List;

public interface RoleService extends IService<Role> {
    List<Role> getByUser(User user, Long offset);

    List<Role> getByUser(Long uid, Long offset);

    List<Role> getByTeam(Team team, Long offset);

    List<Role> getByTeam(Long tid, Long offset);

    Boolean setPerMission(Long rid, Permission permission);

    Boolean setPerMission(Role role, Permission permission);

    Boolean deleteAllWithTeam(Long tid);

    Role getByTeamAndRoleName(Long tid, String name);

    Role getByTeamAndRoleName(Team team, String name);

    Boolean linkedWithRole(Long uid, Long rid);

    Boolean linkedWithRole(User user, Role role);

}
