package com.csb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csb.dto.UserParam;
import com.csb.mapper.UserMapper;
import com.csb.pojo.Team;
import com.csb.pojo.User;
import com.csb.service.UserService;
import com.csb.utils.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Leo
 * @description 针对表【t_user】的数据库操作Service实现
 * @createDate 2023-03-16 10:10:43
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public User loginWithPassword(UserParam userParam) {
        String username = userParam.getUsername();
        String password = userParam.getPassword();
        if (Asset.isNull(username)||Asset.isNull(password)){
            return null;
        }
        User user = userMapper.getByUsernameAndPassword(username, password);
        return user;
    }

    @Override
    public User loginWithPhone(UserParam userParam) {
        return null;
    }

    @Override
    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    @Override
    public User getById(Long id) {
        return super.getById(id);
    }

    @Override
    public List<User> getByTeam(Team team, long offset) {
        return userMapper.getByTid(team.getTid(), offset);
    }

    @Override
    public boolean register(UserParam userParam) {
        // TODO 根据不同方式验证
        return false;
    }

    @Override
    public boolean modifyUser(UserParam userParam) {
        String username = userParam.getUsername();
        String description = userParam.getDescription();
        if (username == null) {
            return false;
        }
        User byUsername = userMapper.getByUsername(username);
        if (byUsername == null) {
            return false;
        }
        if (description == null) {
            return false;
        }
        byUsername.setDescription(description);
        return userMapper.updateById(byUsername) == 1;
    }

    public boolean changePassword(UserParam userParam) {
        String username = userParam.getUsername();
        if (username == null) {
            return false;
        }
        User byUsername = userMapper.getByUsername(username);
        if (byUsername == null) {
            return false;
        }
        if (userParam.getNewPassword() == null) {
            return false;
        }
        byUsername.setPassword(userParam.getNewPassword());
        return userMapper.updateById(byUsername) == 1;
    }
}




