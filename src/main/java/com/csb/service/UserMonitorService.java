package com.csb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csb.pojo.Monitor;
import com.csb.pojo.User;
import com.csb.pojo.UserMonitor;

import java.util.List;

/**
* @author Leo
* @description 针对表【t_user_monitor】的数据库操作Service
* @createDate 2023-03-16 10:10:43
*/
public interface UserMonitorService extends IService<UserMonitor> {
    List<UserMonitor> getByUser(User user,long offset);
    List<UserMonitor> getByMonitor(Monitor monitor,long offset);
    boolean addUserMonitor(User user,Monitor monitor);
    boolean delUserMonitor(User user,Monitor monitor);
    UserMonitor getByUserAndMonitor(User user,Monitor monitor);
}
