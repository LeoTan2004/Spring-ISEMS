package com.csb.service;

import com.baomidou.mybatisplus.service.IService;
import com.csb.pojo.Role;
import com.csb.pojo.User;
import com.csb.pojo.UserRole;

import java.util.List;

/**
* @author Leo
* @description 针对表【t_user_role】的数据库操作Service
* @createDate 2023-03-16 10:10:43
*/
public interface UserRoleService extends IService<UserRole> {
    boolean addUserRole(User user,Role role);
    boolean delUserRole(User user,Role role);
    List<UserRole> getByUser(User user,long offset);
    List<UserRole> getByRole(Role role,long offset);
}
