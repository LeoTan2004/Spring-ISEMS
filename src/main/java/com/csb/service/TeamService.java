package com.csb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csb.pojo.Team;
import com.csb.pojo.User;

import java.util.List;

/**
 * @author Leo
 * @description 针对表【t_team】的数据库操作Service
 * @createDate 2023-03-16 10:10:43
 */
public interface TeamService extends IService<Team> {
    Team getById(Long id);

    List<Team> getByAdmin(User admin, long offset);

    boolean delTeam(Team team);

    boolean setTeamName(Team team, String name);

    boolean setTeamAdmin(Team team, User admin);

    boolean addTeam(Team team);
}

