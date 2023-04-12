package com.csb.api.admin;

import com.alibaba.fastjson.JSON;
import com.csb.dto.UserParam;
import com.csb.pojo.*;
import com.csb.service.*;
import com.csb.utils.Asset;
import com.csb.utils.AuthorityCheck;
import com.csb.vo.MSG;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 管理员可使用,
 * 可以用户设备权限管理
 * 可以用户角色关联管理
 * 可以转移admin
 * 可以管理用户(通过申请,删除用户)
 * 添加/删除设备
 */

@RestController
@Slf4j
@RequestMapping("/admin")
@Scope("session")
public class TeamManageController {
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
    private AuthorityCheck authorityCheck;

    @PostMapping("/userMonitor")
    public MSG setUserMonitor(long uid, long mid, @RequestParam("switch") int turn) {
        User user = userService.getById(uid);
        Monitor monitor = monitorService.getById(mid);
        if (Asset.isNull(user) || Asset.isNull(monitor)) {
            return MSG.ILLEAGAL_PARAM;
        }
        Long relTid = monitor.getRelTid();
        Team team = teamService.getById(relTid);
        if (null!=team && !authorityCheck.isAdmin(team)){
            return MSG.ILLEAGAL_AUTH;
        }
        return switch (turn) {
            case 1 ->//open
                    userMonitorService.addUserMonitor(user, monitor) ? MSG.SUCESS_EMP : MSG.FAIL_EMP;
            case -1 ->//close
                    userMonitorService.delUserMonitor(user, monitor) ? MSG.SUCESS_EMP : MSG.FAIL_EMP;
            default -> MSG.FAIL_EMP;
        };
    }

    @PostMapping("/userRole")
    public MSG setUserRole(long uid, @RequestParam("roleName") String roleName, long tid) {
        Team team = teamService.getById(tid);
        if (Asset.isNull(team)) {
            return MSG.ILLEAGAL_PARAM;
        }
        if (!authorityCheck.isAdmin(team)) {
            return MSG.ILLEAGAL_AUTH;
        }
        User user = userService.getById(uid);
        if (Asset.isNull(user)) {
            return MSG.ILLEAGAL_PARAM;
        }
        Role role = roleService.getByTeamAndName(team, roleName);
        if (Asset.isNull(role)) {
            return MSG.ILLEAGAL_PARAM;
        }
        return userRoleService.addUserRole(user, role) ? MSG.SUCESS_EMP : MSG.FAIL_EMP;
    }

    @PostMapping("/roleModify")
    public MSG setRole(@RequestBody List<String> permission, @RequestParam("role") String roleName, long tid) {
        Team team = teamService.getById(tid);
        if (Asset.isNull(team)) {
            return MSG.ILLEAGAL_PARAM;
        }
        if (!authorityCheck.isAdmin(team)) {
            return MSG.ILLEAGAL_AUTH;
        }
        Role role = roleService.getByTeamAndName(team, roleName);
        if (Asset.isNull(role)) {
            return MSG.ILLEAGAL_PARAM;
        }
        role.setPermission(JSON.toJSONString(permission));
        return roleService.updateById(role) ? MSG.SUCESS_EMP : MSG.FAIL_EMP;
    }

    @PostMapping("/setAdmin")
    public MSG setAdmin(long tid, @ModelAttribute UserParam userParam) {
        Team team = teamService.getById(tid);
        if (Asset.isNull(team)) {
            return MSG.ILLEAGAL_PARAM;
        }
        if (!authorityCheck.isAdmin(team)) {
            return MSG.ILLEAGAL_AUTH;
        }
        User user = userService.getByUsername(userParam.getUsername());
        if (Asset.isNull(user)){
            return MSG.ILLEAGAL_PARAM;
        }
        TeamUsers byTeamAndUser = teamUsersService.getByTeamAndUser(user, team);
        if (Asset.isNull(byTeamAndUser)) {
            return MSG.ILLEAGAL_PARAM;
        }
        team.setTeamAdmin(user.getUid());
        return teamService.setTeamAdmin(team, user) ? MSG.SUCESS_EMP : MSG.FAIL_EMP;
    }

    @PostMapping("/addMonitor")
    public MSG setMonitor( long tid, long mid, @RequestParam("switch") int turn) {
        Monitor monitor = monitorService.getById(mid);
        if (Asset.isNull(monitor)) {
            return MSG.ILLEAGAL_PARAM;
        }
        Team team = teamService.getById(tid);
        if (Asset.isNull(team)) {
            return MSG.ILLEAGAL_PARAM;
        }
        if (!authorityCheck.isAdmin(team)){
            return MSG.ILLEAGAL_AUTH;
        }
        switch (turn) {
            case 1 -> {//open
                monitor.setRelTid(tid);
                return monitorService.updateById(monitor) ? MSG.SUCESS_EMP : MSG.FAIL_EMP;
            }
            case -1 -> {//close
                monitor.setRelTid(-1L);
                return monitorService.updateById(monitor) ? MSG.SUCESS_EMP : MSG.FAIL_EMP;
            }
            default -> {
                return MSG.FAIL_EMP;
            }
        }
    }

    @PostMapping("/getAllUsers")
    public MSG getAllUsers(long tid,@RequestParam(defaultValue = "0") long offset) {
        Team team = teamService.getById(tid);
        if (Asset.isNull(team)) {
            return MSG.ILLEAGAL_PARAM;
        }
        if (!authorityCheck.isAdmin(team)) {
            return MSG.ILLEAGAL_AUTH;
        }
        List<User> byTeam = userService.getByTeam(team, offset);
        if (Asset.isNull(byTeam)) {
            return MSG.FAIL_EMP;
        }
        return new MSG(1,"成功",byTeam);
    }


    @PostMapping("/setUser")
    public MSG setUser(long tid, long uid, @RequestParam("switch") int turn,@RequestParam(required = false) String description) {
        Team team = teamService.getById(tid);
        if (Asset.isNull(team)) {
            return MSG.ILLEAGAL_PARAM;
        }
        if (!authorityCheck.isAdmin(team)) {
            return MSG.ILLEAGAL_AUTH;
        }
        User user = userService.getById(uid);
        if (Asset.isNull(user)) {
            return MSG.ILLEAGAL_PARAM;
        }
        switch (turn) {
            case 1 -> {//add
                return teamUsersService.addTeamUser(team, user, description) ? MSG.SUCESS_EMP : MSG.FAIL_EMP;
            }
            case -1 -> {//remove
                return teamUsersService.delTeamUser(team, user) ? MSG.SUCESS_EMP : MSG.FAIL_EMP;
            }
            default -> {
                return MSG.FAIL_EMP;
            }
        }
    }


}
