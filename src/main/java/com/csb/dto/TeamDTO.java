package com.csb.dto;

import com.csb.exception.IllegalParamException;
import com.csb.module.team.TeamDO;
import com.csb.module.team.TeamStatusEnum;
import com.csb.utils.Assert;

public record TeamDTO(Long admin, String teamName, String description, TeamStatusEnum teamStatusEnum) {

    public TeamDO toTeamDO() throws IllegalParamException {
        return toTeamDO(null);
    }

    public TeamDO toTeamDO(Long tid) throws IllegalParamException {
        if (isIllegal()) throw new IllegalParamException("队伍参数非法");
        return toTeamDO(null,new TeamDO());
    }

    public TeamDO toTeamDO(Long tid, TeamDO teamDO) throws IllegalParamException {
        if (null == teamDO || isIllegal()) throw new IllegalParamException("队伍参数非法");
        teamDO.setTid(tid);
        teamDO.setAdmin(admin);
        teamDO.setDescription(description);
        teamDO.setTeamName(teamName);
        teamDO.setStatus(teamStatusEnum);
        return teamDO;
    }

    public boolean isIllegal() {
        return (Assert.isEnpty(teamName()) || Assert.isNull(admin()));
    }
}
