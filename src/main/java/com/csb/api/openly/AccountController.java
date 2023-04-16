package com.csb.api.openly;

import com.csb.dto.UserInfoDTO;
import com.csb.module.account.UserInfoDO;
import com.csb.module.authority.UserDO;
import com.csb.service.AccountService;
import com.csb.service.UserService;
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
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private UserService userService;

    @PostMapping("/getCurUser")
    public MSG getCurUser(HttpServletRequest request) {
        UserDO curUser = SessionUtils.getCurUser(request.getSession());
        if (Assert.isNull(curUser)) return MSG.MSG_FAIL;
        return MSG.getMsgSuccessWithData(curUser);
    }

    @PostMapping("/changePassword")
    public MSG changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, HttpServletRequest request) {
        UserDO curUser = SessionUtils.getCurUser(request.getSession());
        if (Assert.isNull(curUser)) return MSG.MSG_ILLEGAL_AUTHORITY;
        assert curUser != null;
        Long uid = curUser.getUid();
        return userService.changePassword(uid, oldPassword, newPassword) ? MSG.MSG_SUCCESS : MSG.MSG_ILLEGAL_AUTHORITY;
    }

    @Autowired
    private AccountService accountService;

    @PostMapping("/updateAccount")
    public MSG updateAccount(UserInfoDTO userInfoDTO, HttpServletRequest request) {
        UserDO curUser = SessionUtils.getCurUser(request.getSession());
        if (Assert.isNull(curUser)) return MSG.MSG_ILLEGAL_AUTHORITY;
        UserInfoDO userInfo = userInfoDTO.toUserInfo(curUser.getUid());
        return accountService.updateById(userInfo) ? MSG.MSG_SUCCESS : MSG.MSG_FAIL;
    }
}
