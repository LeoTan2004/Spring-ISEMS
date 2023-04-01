package com.csb.service;

import com.baomidou.mybatisplus.service.IService;
import com.csb.pojo.Role;
import com.csb.pojo.Team;
import com.csb.pojo.User;

import java.util.List;

/**
* @author Leo
* @description 针对表【t_role】的数据库操作Service
* @createDate 2023-03-16 10:10:43
*/
public interface RoleService extends IService<Role> {
    Role getById(Long id);
    boolean delRole(Role role);
    List<Role> getRoleByTeam(Team team,long offset);
    List<Role> getRoleByUser(User user,long offset);
    Role getByTeamAndName(Team team,String name);
}
