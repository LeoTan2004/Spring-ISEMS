package com.csb.api.admin;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/monitor")
public class MonitorManageController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private MonitorService monitorService;
    @Autowired
    private RoleService roleService;

    @PostMapping("/linkWithTeam")
    public MSG linkWithTeam(@RequestParam(value = "mid", required = true) long mid,
                            @RequestParam(value = "tid", required = true) long tid,
                            @RequestParam(value = "isLink", required = true) boolean isLink,
                            HttpServletRequest request) {
        UserDO curUser = SessionUtils.getCurUser(request.getSession());
        if (Assert.isNull(curUser)) return MSG.MSG_ILLEGAL_AUTHORITY;
        if (!(tid == curUser.getUid())) {
            RoleDO role = roleService.getByTeamAndUser(curUser.getUid(), tid);
            if (Assert.isNull(role) || !role.checkPermission(PermissionEnum.PERMISSION_MANAGE_MONITOR)) {
                return MSG.MSG_ILLEGAL_AUTHORITY;
            }
        }
        MonitorDO monitorDO = monitorService.getById(mid);
        if (Assert.isNull(monitorDO)) return MSG.MSG_ILLEGAL_PARAM;
        return monitorService.linkWithTeam(monitorDO.getMid(), tid, isLink) ? MSG.MSG_SUCCESS : MSG.MSG_FAIL;

    }

}
