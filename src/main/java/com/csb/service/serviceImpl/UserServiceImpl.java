package com.csb.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csb.dto.RegisterDTO;
import com.csb.exception.IllegalParamException;
import com.csb.module.authority.UserDO;
import com.csb.service.auth.AbstractAuthentication;
import com.csb.service.auth.AuthenticationType;
import com.csb.module.authority.UserMapper;
import com.csb.module.role.RoleDO;
import com.csb.module.team.TeamDO;
import com.csb.service.UserService;
import com.csb.utils.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @SuppressWarnings("all")
    public UserDO login(AuthenticationType type, Object... args) {
        Class<? extends AbstractAuthentication> clazz = null;
        if (null == type || null == (clazz = type.getAuthentication())) {
            return null;
        }
        AbstractAuthentication abstractAuthentication = AbstractAuthentication.getInstance((Object) userMapper, clazz);
        Object auth = abstractAuthentication.auth(args);
        if (null != auth && auth instanceof UserDO userDO) {
            return userDO;
        }
        return null;
    }

    @Override
    public Integer register(UserDO userDO) {

        if (null == userDO ||
                null != userMapper.getByUsername(userDO.getUsername()) ||
                null != userMapper.getByPhone(userDO.getPhone())) {
            return null;
        }
        return userMapper.insert(userDO);
    }

    @Override
    public Boolean changePassword(Long uid, String oldPassword, String newPassword) {
        if (Assert.isNull(uid, oldPassword, newPassword)) return null;
        UserDO userDO = userMapper.getByUideAndPWD(uid, oldPassword);
        if (userDO == null) {
            return false;
        }
        int i = userMapper.updatePassword(userDO.getUid(), newPassword);
        return 1 == i;
    }

    @Override
    public List<UserDO> listByTeam(Long tid, Long offset) {
        if (Assert.isNull(tid, offset)) return null;
        return userMapper.listByTeam(tid, offset);
    }

    @Override
    public List<UserDO> listByTeam(TeamDO teamDO, Long offset) {
        if (Assert.isNull(teamDO, offset)) {
            return null;
        }
        return listByTeam(teamDO.getTid(), offset);
    }

    @Override
    public List<UserDO> listByRole(RoleDO roleDO, Long offset) {
        if (Assert.isNull(roleDO, offset)) {
            return null;
        }
        return listByRole(roleDO.getRid(), offset);
    }

    @Override
    public List<UserDO> listByRole(Long rid, Long offset) {
        if (Assert.isNull(rid, offset)) {
            return null;
        }
        return userMapper.listByRole(rid, offset);
    }


    private UserDO getUserFromParam(RegisterDTO param) throws IllegalParamException {
        return param.toUserDO();
    }


}
