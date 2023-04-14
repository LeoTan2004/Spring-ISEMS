package com.csb.service.auth.authenticationImpl;

import com.csb.module.authority.UserDO;
import com.csb.service.auth.AbstractAuthentication;
import com.csb.service.auth.AuthenticationType;
import com.csb.module.authority.UserMapper;

public final class PwdAuth extends AbstractAuthentication<UserDO> {
    private SecurityDataSource<UserDO> source;

    public PwdAuth init(SecurityDataSource<UserDO> source) {
        this.source = source;
        return this;
    }

    public PwdAuth init(UserMapper userMapper) {
        return init((Object... args) -> {
            String username, password;
            if (args.length >= 2 &&
                    null != args[0] && null != (username = args[0].toString()) &&
                    null != args[1] && null != (password = args[1].toString())) {
                return userMapper.getByUsernameAndPWD(username, password);
            }
            return null;
        });
    }

    @Override
    public AuthenticationType getType() {
        return AuthenticationType.AUTHENTICATION_TYPE_PWD;
    }

    @Override
    @SuppressWarnings("all")
    public UserDO auth(Object... args) {
        Object uname = null, pwd = null;
        if (2 < args.length || null == (uname = args[0]) || null == (pwd = args[1])) {
            return null;
        }
        String username = uname.toString();
        String password = pwd.toString();
        if (null == password || null == username) {
            return null;
        }
        return source.getObj(username, password);
    }

    @Override
    public AbstractAuthentication<UserDO> init(Object o) {
        if (o instanceof UserMapper userMapper) {
            return init(userMapper);
        }
        return null;
    }
}
