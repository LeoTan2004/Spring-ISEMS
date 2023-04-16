package com.csb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csb.dto.RegisterDTO;
import com.csb.service.auth.AuthenticationType;
import com.csb.module.authority.UserDO;
import com.csb.module.team.TeamDO;
import com.csb.module.role.RoleDO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends IService<UserDO> {
    UserDO login(AuthenticationType type, Object... args);

    Integer register(UserDO userDO);

    Boolean changePassword(Long uid, String oldPassword, String newPassword);

    List<UserDO> listByTeam(Long tid, Long offset);

    List<UserDO> listByTeam(TeamDO teamDO, Long offset);

    List<UserDO> listByRole(RoleDO roleDO, Long offset);

    List<UserDO> listByRole(Long rid, Long offset);

}
