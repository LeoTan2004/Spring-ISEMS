package com.csb.api.openly;

import com.csb.dto.RegisterDTO;
import com.csb.exception.IllegalParamException;
import com.csb.module.authority.UserDO;
import com.csb.service.UserService;
import com.csb.service.auth.AuthenticationType;
import com.csb.utils.Assert;
import com.csb.utils.SessionUtils;
import com.csb.utils.VerifyCodeUtils;
import com.csb.vo.MSG;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/authority")
public class AuthorityController {
    @Autowired
    private UserService userService;
    private final static Integer codeSize = 4;

    @PostMapping("/login")
    public MSG login(@RequestParam(value = "username", required = true) String username, @RequestParam(value = "password", required = true) String password, HttpServletRequest request) {
        // TODO 2023年4月15日10:32:29 这里后期需要对参数封装,同时开放对于其他方式的验证
        if (Assert.isEnpty(username, password)) return MSG.MSG_ILLEGAL_PARAM;
        UserDO login = userService.login(AuthenticationType.AUTHENTICATION_TYPE_PWD, username, password);
        if (Assert.isNull(login)) return MSG.MSG_FAIL;
        SessionUtils.setCurUser(request.getSession(), login);
        return MSG.MSG_SUCCESS;
    }

    @PostMapping("/register")
    public MSG register(RegisterDTO registerDTO, HttpServletRequest request) {
        if (Assert.isNull(registerDTO)) return MSG.MSG_ILLEGAL_PARAM;
        UserDO userDO = null;
        if (!registerDTO.checkCap(request.getSession())) return MSG.MSG_ILLEGAL_CODE;
        try {
            userDO = registerDTO.toUserDO();
        } catch (IllegalParamException e) {
            return MSG.MSG_ILLEGAL_PARAM;
        }
        Integer register = userService.register(userDO,registerDTO.password());
        return null != register && 1 == register ? MSG.MSG_SUCCESS : MSG.MSG_FAIL;
    }

    @RequestMapping("/getIMG")
    public MSG getImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String s = VerifyCodeUtils.generateVerifyCode(codeSize);
        SessionUtils.setCurCap(request.getSession(), s);
        ServletOutputStream outputStream = response.getOutputStream();
        VerifyCodeUtils.outputImage(120, 40, outputStream, s);
        return MSG.MSG_SUCCESS;
    }

    @RequestMapping("/logout")
    public MSG logout(HttpServletRequest request) {
        SessionUtils.clearSession(request.getSession());
        return MSG.MSG_SUCCESS;
    }

    @RequestMapping("/connectionTest")
    public MSG connectionTest() {
        return MSG.MSG_SUCCESS;
    }
}
