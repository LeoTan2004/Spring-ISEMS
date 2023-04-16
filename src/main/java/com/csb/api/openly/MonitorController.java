package com.csb.api.openly;

import com.csb.module.authority.UserDO;
import com.csb.module.monitor.MonitorDO;
import com.csb.module.role.PermissionEnum;
import com.csb.module.role.RoleDO;
import com.csb.module.team.TeamDO;
import com.csb.service.MonitorService;
import com.csb.service.RoleService;
import com.csb.service.TeamService;
import com.csb.utils.Assert;
import com.csb.utils.SessionUtils;
import com.csb.vo.MSG;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/monitor")
public class MonitorController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private MonitorService monitorService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/getMonitorsList")
    public MSG getMonitors(@RequestParam("tid") long tid, @RequestParam("offset") long offset, HttpServletRequest request) {
        UserDO curUser = SessionUtils.getCurUser(request.getSession());
        if (Assert.isNull(curUser)) return MSG.MSG_ILLEGAL_AUTHORITY;
        TeamDO team = teamService.getById(tid);
        if (Assert.isNull(team)) return MSG.MSG_ILLEGAL_PARAM;
        RoleDO roleDO = roleService.getByTeamAndUser(curUser, team);
        if (Assert.isNull(roleDO) || !roleDO.checkPermission(PermissionEnum.PERMISSION_READ_MONITOR))
            return MSG.MSG_ILLEGAL_AUTHORITY;
        List<MonitorDO> monitorDOS = monitorService.listByTeam(tid, offset);
        if (Assert.isNull(monitorDOS)) return MSG.MSG_FAIL;
        return MSG.getMsgSuccessWithData(monitorDOS);

    }

    @PostMapping("/getMonitor")
    public MSG getMonitor(long mid, HttpServletRequest request) {
        UserDO curUser = SessionUtils.getCurUser(request.getSession());
        if (Assert.isNull(curUser)) return MSG.MSG_ILLEGAL_AUTHORITY;
        MonitorDO monitorDO = monitorService.getById(mid);
        if (Assert.isNull(monitorDO)) return MSG.MSG_ILLEGAL_PARAM;
        TeamDO teamDO = teamService.getByMonitor(monitorDO);
        if (Assert.isNull(teamDO)) return MSG.getMsgSuccessWithData(monitorDO);
        RoleDO roleDO = roleService.getByTeamAndUser(curUser, teamDO);
        if (Assert.isNull(roleDO) || !roleDO.checkPermission(PermissionEnum.PERMISSION_READ_MONITOR))
            return MSG.MSG_ILLEGAL_AUTHORITY;
        return MSG.getMsgSuccessWithData(monitorDO);
    }
}
