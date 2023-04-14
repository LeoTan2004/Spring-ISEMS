package com.csb.service.auth;


import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class Authentication<T> {
    @SuppressWarnings("all")
    private final static Map<Class<? extends Authentication>, Authentication> map = new HashMap<>();

    public abstract AuthenticationType getType();

    public abstract T auth(Object... args);

    public abstract Authentication<T> init(Object o);

    @SuppressWarnings("all")
    public static Authentication<?> getInstance(Object o, Class<? extends Authentication> clazz) {
        Authentication<?> authentication;
        if ((authentication = map.get(clazz)) != null) {
            return authentication;
        }
        try {
            authentication = clazz.newInstance();
            authentication = authentication.init(o);
            map.put(clazz, authentication);
            return authentication;
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("初始化鉴权器错误");
            return null;
        }
    }

}

