package com.csb.api.admin;

import com.csb.dto.TeamDTO;
import com.csb.exception.IllegalParamException;
import com.csb.module.authority.UserDO;
import com.csb.module.team.TeamDO;
import com.csb.service.TeamService;
import com.csb.utils.Assert;
import com.csb.utils.SessionUtils;
import com.csb.vo.MSG;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/private")
public class TeamManageController {
    @Autowired
    private TeamService teamService;


    @PostMapping("/updateTeamInfo")
    public MSG updateTeamInfo(@RequestParam(value = "tid", required = true) long tid, TeamDTO teamDTO,
                              HttpServletRequest request) {
        UserDO user = SessionUtils.getCurUser(request.getSession());
        if (Assert.isNull(user)) return MSG.MSG_ILLEGAL_AUTHORITY;
        if (Assert.isNull(teamDTO) || teamDTO.isIllegal()) return MSG.MSG_ILLEGAL_PARAM;
        TeamDO team = teamService.getById(tid);
        if (Assert.isNull(team)) return MSG.MSG_ILLEGAL_PARAM;
        if (!user.isAdmin(team)) return MSG.MSG_ILLEGAL_AUTHORITY;
        TeamDO teamDO = null;
        try {
            teamDO = teamDTO.toTeamDO(team.getTid(), team);
        } catch (IllegalParamException e) {
            return MSG.MSG_ILLEGAL_PARAM;
        }
        return teamService.updateById(teamDO) ? MSG.MSG_SUCCESS : MSG.MSG_FAIL;
    }

    @PostMapping("/getTeams")
    public MSG listByAdmin(@RequestParam(value = "offset", required = true) long offset, HttpServletRequest request) {
        UserDO user = SessionUtils.getCurUser(request.getSession());
        if (Assert.isNull(user)) return MSG.MSG_ILLEGAL_AUTHORITY;
        List<TeamDO> teams = teamService.listByAdmin(user, offset);
        if (Assert.isNull(teams)) return MSG.MSG_FAIL;
        return MSG.getMsgSuccessWithData(teams);
    }
}
