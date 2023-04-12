package com.csb.api.openly;

import com.csb.dto.AuthorityType;
import com.csb.dto.UserParam;
import com.csb.pojo.User;
import com.csb.service.*;
import com.csb.utils.Asset;
import com.csb.utils.AuthorityCheck;
import com.csb.utils.VerifyCodeUtils;
import com.csb.vo.MSG;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 权限控制,主要包括注册,登录,登出,修改密码等
 */
@RestController
@Slf4j
@RequestMapping("/authority")
@Scope("session")
public class AuthorityController {

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

    @RequestMapping("/test")
    public MSG test(){
        return MSG.SUCESS_EMP;
    }

    @PostMapping("/login")
    public MSG login(UserParam loginParam) {
        if (Asset.isNull(loginParam.getType())) {
            return MSG.ILLEAGAL_PARAM;
        }
        switch (loginParam.getType()) {
            case AuthorityType.USER_PWD -> {
                if (Asset.isNull(loginParam.getUsername()) || Asset.isNull(loginParam.getPassword())) {
                    return MSG.ILLEAGAL_PARAM;
                }
                User user = userService.loginWithPassword(loginParam);
                if (!Asset.isNull(user)){
                    authorityCheck.setCurUser(user);
                }
                return Asset.isNull(user)?MSG.FAIL_EMP:MSG.SUCESS_EMP;
            }
            case AuthorityType.USER_PHONE -> {
                if (Asset.isNull(loginParam.getCaptcha_pic()) || Asset.isNull(loginParam.getCaptcha())) {
                    return MSG.ILLEAGAL_PARAM;
                }
                User user = userService.loginWithPhone(loginParam);
                if (!Asset.isNull(user)){
                    authorityCheck.setCurUser(user);
                }
                return Asset.isNull(user)?MSG.FAIL_EMP:MSG.SUCESS_EMP;
            }
        }
        return MSG.ILLEAGAL_PARAM;
    }

    @PostMapping("/register")
    public MSG register(UserParam registerParam) {
        if (Asset.isNull(registerParam.getUsername()) || Asset.isNull(registerParam.getPassword()) ||
                Asset.isNull(registerParam.getCaptcha()) || Asset.isNull(registerParam.getCaptcha_pic())) {
            return MSG.ILLEAGAL_PARAM;
        }
        String captchaPic = registerParam.getCaptcha_pic();
        if (!authorityCheck.checkPicCaptcha(captchaPic)) {
            return MSG.ILLEAGAL_PARAM;
        }
        return userService.register(registerParam) ? MSG.SUCESS_EMP : MSG.FAIL_EMP;
    }

    @PostMapping("/logout")
    public MSG logout(HttpServletRequest request,HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie: cookies){

            cookie.setMaxAge(0);

            cookie.setPath("/");

            response.addCookie(cookie);

        }
        authorityCheck.setCurUser(null);
        return MSG.SUCESS_EMP;
    }

    @GetMapping("/getIMG")
    public MSG getImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String s = VerifyCodeUtils.generateVerifyCode(5);
        request.getSession().setAttribute(AuthorityCheck.PIC_CODE, s);
        ServletOutputStream outputStream = response.getOutputStream();
        VerifyCodeUtils.outputImage(100, 40, outputStream, s);
        return MSG.SUCESS_EMP;
    }

}
