package com.csb.api.openly;

import com.csb.module.authority.UserDO;
import com.csb.module.role.PermissionEnum;
import com.csb.module.role.RoleDO;
import com.csb.service.RoleService;
import com.csb.service.TeamService;
import com.csb.service.UserService;
import com.csb.utils.Assert;
import com.csb.utils.SessionUtils;
import com.csb.vo.MSG;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @PostMapping("/apply")
    public MSG applyTo(long tid, HttpServletRequest request) {
        UserDO curUser = SessionUtils.getCurUser(request.getSession());
        if (Assert.isNull(curUser)) return MSG.MSG_ILLEGAL_AUTHORITY;
        return teamService.applyToTeam(curUser.getUid(), tid) ? MSG.MSG_SUCCESS : MSG.MSG_FAIL;
    }

    @Autowired
    private RoleService roleService;

    @PostMapping("/getAllUsers")
    public MSG getAllUsers(long tid, long offset, HttpServletRequest request) {
        UserDO curUser = SessionUtils.getCurUser(request.getSession());
        if (Assert.isNull(curUser)) return MSG.MSG_ILLEGAL_AUTHORITY;


        RoleDO roleDO = roleService.getByTeamAndUser(tid, curUser.getUid());
        if (Assert.isNull(roleDO) || !roleDO.checkPermission(PermissionEnum.PERMISSION_READ_MEMBER))
            return MSG.MSG_ILLEGAL_AUTHORITY;
        List<UserDO> userDOS = userService.listByTeam(tid, offset);
        if (Assert.isNull(userDOS)) return MSG.MSG_FAIL;
        return MSG.getMsgSuccessWithData(userDOS);
    }

    @PostMapping("/quitTeam")
    public MSG quitTeam(long tid, HttpServletRequest request) {
        UserDO curUser = SessionUtils.getCurUser(request.getSession());
        if (Assert.isNull(curUser)) return MSG.MSG_ILLEGAL_AUTHORITY;
        return teamService.quitTeam(curUser.getUid(), tid) ? MSG.MSG_SUCCESS : MSG.MSG_FAIL;
    }

}
