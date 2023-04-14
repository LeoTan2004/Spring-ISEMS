package com.csb.service.auth;

import com.csb.service.auth.authenticationImpl.EmailAuth;
import com.csb.service.auth.authenticationImpl.PhoneAuth;
import com.csb.service.auth.authenticationImpl.PwdAuth;

public enum AuthenticationType {
    AUTHENTICATION_TYPE_PWD(1,(N)->{return "登录失败,账号或密码错误";},(N)->{return "登录成功";}, PwdAuth.class),
    AUTHENTICATION_TYPE_PHONE(1<<2,(N)->{return "验证失败";},(N)->{return "验证成功";}, PhoneAuth.class),
    AUTHENTICATION_TYPE_EMAIL(1<<4,(N)->{return "验证失败";},(N)->{return "验证成功";}, EmailAuth.class);

    private final Integer code;
    private final Actionable whenSuc;
    private final Actionable whenFail;
    private final Class<? extends AbstractAuthentication> authentication;

    AuthenticationType(Integer code, Actionable whenSuc, Actionable whenFail, Class<? extends AbstractAuthentication> authentication1) {
        this.code = code;
        this.whenSuc = whenSuc;
        this.whenFail = whenFail;
        this.authentication = authentication1;
    }

    public Integer getCode() {
        return code;
    }

    public Class<? extends AbstractAuthentication> getAuthentication() {
        return authentication;
    }

    public Actionable getWhenSuc() {
        return whenSuc;
    }

    public Actionable getWhenFail() {
        return whenFail;
    }
}

interface Actionable {
    Object action(Object...args);
}
