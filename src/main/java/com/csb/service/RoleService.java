package com.csb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csb.module.authority.UserDO;
import com.csb.module.role.PermissionEnum;
import com.csb.module.role.RoleDO;
import com.csb.module.team.TeamDO;

import java.util.List;

public interface RoleService extends IService<RoleDO> {
    List<RoleDO> listByUser(UserDO userDO, Long offset);

    List<RoleDO> listByUser(Long uid, Long offset);

    List<RoleDO> listByTeam(TeamDO teamDO, Long offset);

    List<RoleDO> listByTeam(Long tid, Long offset);

    Boolean setPerMission(Long rid, PermissionEnum permissionEnum);

    Boolean setPerMission(RoleDO roleDO, PermissionEnum permissionEnum);

    Boolean deleteAllWithTeam(Long tid);

    RoleDO getByTeamAndRoleName(Long tid, String name);

    RoleDO getByTeamAndRoleName(TeamDO teamDO, String name);

    Boolean linkedWithRole(Long uid, Long rid);

    Boolean linkedWithRole(UserDO userDO, RoleDO roleDO);

    Boolean initForTeam(TeamDO teamDO);

    Boolean initForTeam(Long tid);

    Boolean deleteByUserAndTeam(Long uid, Long tid);

    Boolean deleteByUserAndTeam(UserDO userDo, TeamDO teamDO);

    RoleDO getByTeamAndUser(UserDO userDO,TeamDO teamDO);
    RoleDO getByTeamAndUser(Long uid,Long tid);

    Boolean deleteById(Long rid);
    Boolean deleteById(RoleDO role);

}
