package com.csb.api.openly;

import com.csb.pojo.Team;
import com.csb.pojo.User;
import com.csb.service.*;
import com.csb.utils.Asset;
import com.csb.utils.AuthorityCheck;
import com.csb.vo.MSG;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 可以申请加入小组
 * 可以查询小组内成员
 * 可以退出小组
 * 可以查询小组的基本信息
 */
@RestController
@Slf4j
@RequestMapping("/team")
@Scope("session")
public class TeamController {
    @Autowired
    private UserService userService;
    @Autowired
    private MonitorService monitorService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private TeamUsersService teamUsersService;
    @Autowired
    private UserMonitorService userMonitorService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private TeamLogService logService;
    @Autowired
    AuthorityCheck authorityCheck;

    @PostMapping("/apply")
    public MSG applyTo(long tid, String description) {
        Team team = teamService.getById(tid);
        if (Asset.isNull(team)) {
            return MSG.ILLEAGAL_PARAM;
        }
        User curUser = authorityCheck.getCurUser();
        return teamUsersService.applyToTeam(team, curUser, description) ? MSG.SUCESS_EMP : MSG.FAIL_EMP;
    }

    @PostMapping("/getAllUsers")
    public MSG getAllUsers(long tid,long offset) {
        Team byId = teamService.getById(tid);
        if (Asset.isNull(byId)){
            return MSG.ILLEAGAL_PARAM;
        }
        List<User> byTeam = userService.getByTeam(byId, offset);
        if (Asset.isNull(byTeam)){
            return MSG.FAIL_EMP;
        }
        return new MSG(byTeam);
    }

    @PostMapping("/quitTeam")
    public MSG quitTeam(long tid) {
        User curUser = authorityCheck.getCurUser();
        Team byId = teamService.getById(tid);
        if (Asset.isNull(byId)){
            return MSG.ILLEAGAL_PARAM;
        }
        return teamUsersService.delTeamUser(byId, curUser) ? MSG.SUCESS_EMP : MSG.FAIL_EMP;
    }

    @PostMapping("/newTeam")
    public MSG addTeam(String teamName){
        User curUser = authorityCheck.getCurUser();
        if (Asset.isNull(curUser)){
            return MSG.ILLEAGAL_AUTH;
        }
        Team team = new Team(null, null, teamName, curUser.getUid());
        return teamService.addTeam(team)?MSG.SUCESS_EMP:MSG.FAIL_EMP;
    }
}
