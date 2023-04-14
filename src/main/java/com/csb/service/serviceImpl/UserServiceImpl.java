package com.csb.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csb.dto.RegisterParam;
import com.csb.service.auth.Authentication;
import com.csb.service.auth.AuthenticationType;
import com.csb.module.authority.User;
import com.csb.module.authority.UserMapper;
import com.csb.module.role.Role;
import com.csb.module.team.Team;
import com.csb.service.UserService;
import com.csb.utils.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @SuppressWarnings("all")
    public User login(AuthenticationType type, Object... args) {
        Class<? extends Authentication> clazz = null;
        if (null == type || null == (clazz = type.getAuthentication())) {
            return null;
        }
        Authentication authentication = Authentication.getInstance((Object) userMapper, clazz);
        Object auth = authentication.auth(args);
        if (null != auth && auth instanceof User user) {
            return user;
        }
        return null;
    }

    @Override
    public Integer register(RegisterParam param) {
        User user = getUserFromParam(param);
        if (null == user ||
                null != userMapper.getByUsername(user.getUsername()) ||
                null != userMapper.getByPhone(user.getPhone())) {
            return null;
        }
        return userMapper.insert(user);
    }

    @Override
    public Boolean changePassword(Long uid, String oldPassword, String newPassword) {
        if (Assert.isNull(uid, oldPassword, newPassword)) return null;
        User user = userMapper.getByUideAndPWD(uid, oldPassword);
        if (user == null) {
            return false;
        }
        int i = userMapper.updatePassword(user.getUid(), newPassword);
        return 1 == i;
    }

    @Override
    public List<User> getByTeam(Long tid, Long offset) {
        if (Assert.isNull(tid, offset)) return null;
        return userMapper.getByTeam(tid, offset);
    }

    @Override
    public List<User> getByTeam(Team team, Long offset) {
        if (Assert.isNull(team, offset)) {
            return null;
        }
        return getByTeam(team.getTid(), offset);
    }

    @Override
    public List<User> getByRole(Role role, Long offset) {
        if (Assert.isNull(role, offset)) {
            return null;
        }
        return getByRole(role.getRid(), offset);
    }

    @Override
    public List<User> getByRole(Long rid, Long offset) {
        if (Assert.isNull(rid, offset)) {
            return null;
        }
        return userMapper.getByRole(rid, offset);
    }


    private User getUserFromParam(RegisterParam param) {
        String username = param.getUsername();
        String password = param.getPassword();
        Long phone = param.getPhone();
        if (!Assert.isEnpty(username, password) && null != phone) {
            return new User(null, username, phone, null, null, null, null);
        }
        return null;
    }


}
