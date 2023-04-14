package com.csb.service.auth.authenticationImpl;

public interface SecurityDataSource<T> {
    T getObj(Object... keys);
}
