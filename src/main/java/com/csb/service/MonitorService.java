package com.csb.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.csb.pojo.Monitor;
import com.csb.pojo.Team;
import com.csb.pojo.User;

import java.util.List;

/**
* @author Leo
* @description 针对表【t_monitor】的数据库操作Service
* @createDate 2023-03-16 10:10:43
*/
public interface MonitorService extends IService<Monitor> {
    Monitor getById(Long id);
    List<Monitor> getByTeam(Team team,long offset);
    List<Monitor> getByUser(User user,long offset);
}
