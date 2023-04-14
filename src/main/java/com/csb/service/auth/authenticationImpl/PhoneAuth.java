package com.csb.service.auth.authenticationImpl;

import com.csb.module.authority.UserDO;
import com.csb.service.auth.AbstractAuthentication;
import com.csb.service.auth.AuthenticationType;

@Deprecated
public final class PhoneAuth extends AbstractAuthentication<UserDO> {
    private SecurityDataSource<UserDO> source;
    @Override
    public AuthenticationType getType() {
        return AuthenticationType.AUTHENTICATION_TYPE_PHONE;
    }

    @Override
    public UserDO auth(Object... args) {
        Object phone = null, code = null;
        if (2 != args.length && (phone = args[0]) != null && (code = args[1]) != null) {
            return null;
        }
        return null;
    }

    @Override
    public AbstractAuthentication<UserDO> init(Object o) {
        if (o instanceof SecurityDataSource<?>){
            SecurityDataSource<UserDO> src = (SecurityDataSource<UserDO>) o;
            return init(src);
        }
        return null;
    }

    public AbstractAuthentication<UserDO> init(SecurityDataSource<UserDO> source) {
        this.source =  source;
        return this;
    }
}
