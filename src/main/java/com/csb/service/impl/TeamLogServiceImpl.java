package com.csb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csb.mapper.TeamLogMapper;
import com.csb.pojo.Team;
import com.csb.pojo.TeamLog;
import com.csb.service.TeamLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author Leo
 * @description 针对表【t_example_log】的数据库操作Service实现
 * @createDate 2023-03-16 10:10:43
 */
@Service
public class TeamLogServiceImpl extends ServiceImpl<TeamLogMapper, TeamLog>
        implements TeamLogService {

    @Autowired
    private TeamLogMapper logMapper;

    @Override
    public List<TeamLog> getTeamLog(Team team, long offset) {
        long time = new Timestamp(System.currentTimeMillis()).getTime() - 1000 * 60 * 60 * 24;
        return this.getTeamLogWithinTime(team, new Date(time), new Date(), offset);
    }

    @Override
    public List<TeamLog> getTeamLogWithinTime(Team team, Date sDate, Date eDate, long offset) {
        return logMapper.getByTime(team.getTid(), new java.sql.Date(sDate.getTime()), new java.sql.Date(sDate.getTime()), offset);
    }

    @Override
    public boolean insertTeamLog(Team team, TeamLog teamLog) {
        teamLog.setTid(team.getTid());
        return logMapper.insert(teamLog) == 1;
    }
}




