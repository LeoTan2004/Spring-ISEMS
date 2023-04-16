package com.csb.api.admin;


import com.csb.dto.RoleDTO;
import com.csb.exception.IllegalParamException;
import com.csb.module.authority.UserDO;
import com.csb.module.role.RoleDO;
import com.csb.module.team.TeamDO;
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
@RequestMapping("/admin/role")
public class RoleManageController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private RoleService roleService;

    @PostMapping("/updateRole")
    public MSG updateRole(RoleDTO roleDTO, HttpServletRequest request) {
        UserDO user = SessionUtils.getCurUser(request.getSession());
        if (Assert.isNull(user)) return MSG.MSG_ILLEGAL_AUTHORITY;
        RoleDO roleDO = null;
        try {
            roleDO = roleDTO.toRoleDO(roleDTO.rid());
        } catch (IllegalParamException e) {
            return MSG.MSG_ILLEGAL_PARAM;
        }
        TeamDO team = teamService.getById(roleDO.getRelTid());
        if (!user.isAdmin(team)) {
            return MSG.MSG_ILLEGAL_AUTHORITY;
        }
        return roleService.updateById(roleDO) ? MSG.MSG_SUCCESS : MSG.MSG_FAIL;
    }

    @PostMapping("/addRole")
    public MSG addRole(RoleDTO roleDTO, HttpServletRequest request) {
        UserDO user = SessionUtils.getCurUser(request.getSession());
        if (Assert.isNull(user)) return MSG.MSG_ILLEGAL_AUTHORITY;
        RoleDO roleDO = null;
        try {
            roleDO = roleDTO.toRoleDO();
        } catch (IllegalParamException e) {
            return MSG.MSG_ILLEGAL_PARAM;
        }
        TeamDO team = teamService.getById(roleDO.getRelTid());
        if (!user.isAdmin(team)) {
            return MSG.MSG_ILLEGAL_AUTHORITY;
        }
        return roleService.save(roleDO) ? MSG.MSG_SUCCESS : MSG.MSG_FAIL;
    }

    @PostMapping("/removeRole")
    public MSG removeRole(@RequestParam(value = "rid", required = true) Long rid, HttpServletRequest request) {
        UserDO user = SessionUtils.getCurUser(request.getSession());
        if (Assert.isNull(user)) return MSG.MSG_ILLEGAL_AUTHORITY;
        RoleDO role = roleService.getById(rid);
        if (Assert.isNull(role)) return MSG.MSG_ILLEGAL_PARAM;
        Long tid = role.getRelTid();
        TeamDO team = teamService.getById(tid);
        if (Assert.isNull(team)) return MSG.MSG_ERROR;
        if (user.isAdmin(team)) {
            return MSG.MSG_ILLEGAL_AUTHORITY;
        }
        return roleService.deleteById(role) ? MSG.MSG_SUCCESS : MSG.MSG_FAIL;
    }
}
