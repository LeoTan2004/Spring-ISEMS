package com.csb.api.openly;

import com.csb.dto.AuthorityType;
import com.csb.dto.UserParam;
import com.csb.pojo.User;
import com.csb.service.*;
import com.csb.utils.Asset;
import com.csb.utils.AuthorityCheck;
import com.csb.vo.MSG;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 可以修改用户描述
 * 可以修改用户密码
 */
@RestController
@Slf4j
@RequestMapping("/account")
@Scope("session")
public class AccountController {

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
    HttpServletRequest request;
    @Autowired
    AuthorityCheck authorityCheck;


    @PostMapping("/introduce")
    public MSG setDescription(String introduction,String nickname) {
        User curUser = authorityCheck.getCurUser();
        if (Asset.isNull(curUser)){
            return MSG.ILLEAGAL_AUTH;
        }
        curUser.setIntroduction(introduction);
        curUser.setNickname(nickname);
        return userService.updateById(curUser) ? MSG.SUCESS_EMP : MSG.FAIL_EMP;

    }

    @PostMapping("/changePassword")
    public MSG changePassword(UserParam userParam) {
        User user = authorityCheck.getCurUser();
        if (Asset.isNull(user)) {
            return MSG.ILLEAGAL_AUTH;
        }
        if (Asset.isNull(userParam.getNewPassword())) {
            return MSG.ILLEAGAL_PARAM;
        }
        if (!Asset.isNull(userParam.getPassword())) {
            userParam.setType(AuthorityType.USER_PWD);
            return _changepwd(userParam, user);
        }
        if (!(Asset.isNull(userParam.getCaptcha_pic()) || Asset.isNull(userParam.getCaptcha()))) {
            userParam.setType(AuthorityType.USER_PHONE);
            return _changepwd(userParam, user);
        }
        return MSG.ILLEAGAL_AUTH;
    }

    private MSG _changepwd(UserParam userParam, User user) {
        userParam.setUsername(user.getUsername());
        if (!Asset.isNull(userService.loginWithPassword(userParam))) {
            user.setPassword(userParam.getNewPassword());
            boolean b = userService.updateById(user);
            if (b) {
                authorityCheck.setCurUser(null);
            }
            return b ? MSG.SUCESS_EMP : MSG.FAIL_EMP;
        }
        return MSG.ILLEAGAL_AUTH;
    }

    @PostMapping("/getCurUser")
    public MSG getCurUser() {
        User curUser = authorityCheck.getCurUser();
        if (Asset.isNull(curUser)) {
            return MSG.ILLEAGAL_AUTH;
        }
        return new MSG(curUser);
    }


}
