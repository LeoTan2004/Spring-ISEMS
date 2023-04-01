package com.csb.service;

import com.baomidou.mybatisplus.service.IService;
import com.csb.pojo.Team;
import com.csb.pojo.TeamLog;

import java.util.Date;
import java.util.List;

/**
 * @author Leo
 * @description 针对表【t_example_log】的数据库操作Service
 * @createDate 2023-03-16 10:10:43
 */
public interface TeamLogService extends IService<TeamLog> {
    List<TeamLog> getTeamLog(Team team,long offset);

    List<TeamLog> getTeamLogWithinTime(Team team, Date sDate, Date eDate,long offset);

    boolean insertTeamLog(Team team, TeamLog teamLog);

}
