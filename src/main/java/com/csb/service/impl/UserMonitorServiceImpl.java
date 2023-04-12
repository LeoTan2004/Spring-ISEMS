package com.csb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csb.mapper.UserMonitorMapper;
import com.csb.pojo.Monitor;
import com.csb.pojo.User;
import com.csb.pojo.UserMonitor;
import com.csb.service.UserMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Leo
 * @description 针对表【t_user_monitor】的数据库操作Service实现
 * @createDate 2023-03-16 10:10:43
 */
@Service
public class UserMonitorServiceImpl extends ServiceImpl<UserMonitorMapper, UserMonitor>
        implements UserMonitorService {
    @Autowired
    private UserMonitorMapper mapper;


    @Override
    public List<UserMonitor> getByUser(User user, long offset) {
        return mapper.getByUid(user.getUid(), offset);
    }

    @Override
    public List<UserMonitor> getByMonitor(Monitor monitor, long offset) {
        return mapper.getByMid(monitor.getMid(), offset);
    }

    @Override
    public boolean addUserMonitor(User user, Monitor monitor) {
        return mapper.insert(new UserMonitor(user.getUid(), monitor.getMid())) == 1;
    }

    @Override
    public boolean delUserMonitor(User user, Monitor monitor) {
        return mapper.deleteByRelMidAndRelUid(monitor.getMid(), user.getUid()) == 1;
    }

    @Override
    public UserMonitor getByUserAndMonitor(User user, Monitor monitor) {
        return mapper.getByRelMidAndRelUid(monitor.getMid(), user.getUid());
    }
}




