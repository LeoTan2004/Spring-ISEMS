package com.csb.service.auth.authenticationImpl;

import com.csb.service.auth.Authentication;
import com.csb.service.auth.AuthenticationType;
import com.csb.module.authority.User;

@Deprecated
public final class EmailAuth extends Authentication<User> {
    private SecurityDataSource<User> source;
    @Override
    public AuthenticationType getType() {
        return AuthenticationType.AUTHENTICATION_TYPE_EMAIL;
    }

    @Override
    public User auth(Object... args) {
        Object email = null, code = null;
        if (2 != args.length && (email = args[0]) != null && (code = args[1]) != null) {
            return null;
        }
        return null;
    }

    @Override
    public Authentication<User> init(Object o) {
        if (o instanceof SecurityDataSource<?>){
            SecurityDataSource<User> src = (SecurityDataSource<User>) o;
            return init(src);
        }
        return null;
    }

    public Authentication<User> init(SecurityDataSource<User> source) {
        this.source =  source;
        return this;
    }
}
