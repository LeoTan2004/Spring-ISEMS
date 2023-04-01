package com.csb.service;

import com.baomidou.mybatisplus.service.IService;
import com.csb.dto.UserParam;
import com.csb.pojo.Team;
import com.csb.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Leo
* @description 针对表【t_user】的数据库操作Service
* @createDate 2023-03-16 10:10:43
*/
@Service
public interface UserService extends IService<User> {
    User loginWithPassword(UserParam userParam);
    User loginWithPhone(UserParam userParam);
    User getByUsername(String username);
    User getById(Long id);
    List<User> getByTeam(Team team, long offset);
    boolean register(UserParam userParam);
    boolean modifyUser(UserParam userParam);
    public boolean changePassword(UserParam userParam);
}
