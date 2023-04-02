package com.csb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csb.mapper.UserRoleMapper;
import com.csb.pojo.Role;
import com.csb.pojo.User;
import com.csb.pojo.UserRole;
import com.csb.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Leo
 * @description 针对表【t_user_role】的数据库操作Service实现
 * @createDate 2023-03-16 10:10:43
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    @Autowired
    private UserRoleMapper mapper;


    @Override
    public boolean addUserRole(User user, Role role) {
        return mapper.insert(new UserRole(user.getUid(), role.getRid())) == 1;
    }

    @Override
    public boolean delUserRole(User user, Role role) {
        return mapper.deleteByRelRoleIdAndRelRoleId(role.getRid(), user.getUid()) == 1;
    }

    @Override
    public List<UserRole> getByUser(User user, long offset) {
        return mapper.getAllByUserId(user.getUid(), offset);
    }

    @Override
    public List<UserRole> getByRole(Role role, long offset) {
        return mapper.getAllByRoleId(role.getRid(), offset);
    }
}




