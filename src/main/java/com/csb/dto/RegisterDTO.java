package com.csb.dto;

import com.csb.exception.IllegalParamException;
import com.csb.module.authority.UserDO;
import com.csb.utils.Assert;
import com.csb.utils.SessionUtils;
import jakarta.servlet.http.HttpSession;

/**
 * 注册时需要的基本的数据集
 */

public record RegisterDTO(String username, String password, Long phone, String email, String pic_cap) {
    public UserDO toBasicUserDO() throws IllegalParamException {
        if (this.isIllegal()) {
            throw new IllegalParamException("用户注册参数非法");
        }
        UserDO userDO = new UserDO();
        userDO.setPhone(phone);
        userDO.setUsername(username);
        userDO.setPassword(password);
        return userDO;
    }

    public UserDO toUserDO() throws IllegalParamException {
        UserDO userDO = toBasicUserDO();
        userDO.setEmail(email);
        return userDO;
    }

    private boolean isIllegal() {
        return !Assert.isEnpty(username, password, pic_cap) && !Assert.isNull(phone);
    }

    public boolean checkCap(HttpSession session) {
        if (Assert.isNull(session)) return false;
        return SessionUtils.authPicCaptcha(pic_cap, session);
    }
}
