package com.csb.api.openly;

import com.csb.pojo.*;
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

@RestController
@Slf4j
@RequestMapping("/monitor")
@Scope("session")
public class MonitorController {
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

    @PostMapping("/getMonitorsList")
    public MSG getMonitors(long tid, long offset) {
        Team team = teamService.getById(tid);
        if (Asset.isNull(team)) {
            return MSG.ILLEAGAL_PARAM;
        }
        List<Monitor> byTeam = monitorService.getListByTeam(team, offset);
        if (Asset.isNull(byTeam)) {
            return MSG.FAIL_EMP;
        }
        return new MSG(byTeam);

    }

    @PostMapping("/getMonitor")
    public MSG getMonitor(long mid) {
        Monitor monitor = monitorService.getById(mid);
        if (Asset.isNull(monitor)) {
            return MSG.ILLEAGAL_PARAM;
        }
        UserMonitor relation = userMonitorService.getByUserAndMonitor(authorityCheck.getCurUser(), monitor);

        if (!Asset.isNull(relation)) {
            return new MSG(monitor);
        } else {
            Team team = teamService.getById(monitor.getRelTid());
            if (Asset.isNull(team)) {
                return new MSG(monitor);
            } else if (authorityCheck.isAdmin(team)) {
                return new MSG(monitor);
            }
        }
        return MSG.ILLEAGAL_AUTH;
    }
}
