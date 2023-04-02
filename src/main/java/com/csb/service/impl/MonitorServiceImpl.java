package com.csb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csb.mapper.MonitorMapper;
import com.csb.pojo.Monitor;
import com.csb.pojo.Team;
import com.csb.pojo.User;
import com.csb.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Leo
* @description 针对表【t_monitor】的数据库操作Service实现
* @createDate 2023-03-16 10:10:43
*/
@Service
public class MonitorServiceImpl extends ServiceImpl<MonitorMapper, Monitor>
    implements MonitorService {
    @Autowired
    private MonitorMapper monitorMapper;

    @Override
    public Monitor getById(Long id) {
        return monitorMapper.selectById(id);
    }

    @Override
    public List<Monitor> getByTeam(Team team, long offset) {
        return monitorMapper.getByTid(team.getTid(),offset);
    }

    @Override
    public List<Monitor> getByUser(User user, long offset) {
        return monitorMapper.getByUser(user.getUid(), offset);
    }
}




