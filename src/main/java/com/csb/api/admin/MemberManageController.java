package com.csb.api.admin;

import com.csb.module.authority.UserDO;
import com.csb.module.role.PermissionEnum;
import com.csb.module.role.RoleDO;
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

import java.util.List;
import java.util.Objects;

@RequestMapping("/admin/member")
@RestController
public class MemberManageController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private RoleService roleService;

    @PostMapping("/bindRole")
    public MSG bindMemberWithRole(@RequestParam("uid") long uid,
                                  @RequestParam("rid") long rid,
                                  HttpServletRequest request) {
        UserDO curUser = SessionUtils.getCurUser(request.getSession());
        if (Assert.isNull(curUser)) return MSG.MSG_ILLEGAL_AUTHORITY;
        RoleDO role = roleService.getById(rid);
        if (Assert.isNull(role)) return MSG.MSG_ILLEGAL_PARAM;
        Long tid = role.getRelTid();
        if (!checkAuth(tid,curUser))return MSG.MSG_ILLEGAL_AUTHORITY;
        return roleService.linkedWithRole(uid, rid) ? MSG.MSG_SUCCESS : MSG.MSG_FAIL;
    }

    @PostMapping("/allRoles")
    public MSG getAllRoleByTeam(@RequestParam("tid")Long tid,@RequestParam("offset")Long offset,HttpServletRequest request){
        UserDO curUser = SessionUtils.getCurUser(request.getSession());
        if (Assert.isNull(curUser))return MSG.MSG_ILLEGAL_AUTHORITY;
        if (!checkAuth(tid,curUser))return MSG.MSG_ILLEGAL_AUTHORITY;
        List<RoleDO> roleDOS = roleService.listByTeam(tid, offset);
        if (Assert.isNull(roleDOS)) return MSG.MSG_ERROR;
        return MSG.getMsgSuccessWithData(roleDOS);
    }

    private boolean checkAuth(Long tid,UserDO curUser){
        if (!(Objects.equals(tid, curUser.getUid()))) {
            RoleDO myRole = roleService.getByTeamAndUser(curUser.getUid(), tid);
            return myRole.checkPermission(PermissionEnum.PERMISSION_MANAGE_MEMBER);
        }
        return true;
    }
}
