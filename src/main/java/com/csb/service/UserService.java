package com.csb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csb.dto.RegisterParam;
import com.csb.service.auth.AuthenticationType;
import com.csb.module.authority.User;
import com.csb.module.team.Team;
import com.csb.module.role.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends IService<User> {
    User login(AuthenticationType type, Object... args);

    Integer register(RegisterParam user);

    Boolean changePassword(Long uid, String oldPassword, String newPassword);

    List<User> getByTeam(Long tid, Long offset);

    List<User> getByTeam(Team team, Long offset);

    List<User> getByRole(Role role, Long offset);

    List<User> getByRole(Long rid, Long offset);

}
