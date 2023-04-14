package com.csb.service.auth;


import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class AbstractAuthentication<T> {
    @SuppressWarnings("all")
    private final static Map<Class<? extends AbstractAuthentication>, AbstractAuthentication> map = new HashMap<>();

    public abstract AuthenticationType getType();

    public abstract T auth(Object... args);

    public abstract AbstractAuthentication<T> init(Object o);

    @SuppressWarnings("all")
    public static AbstractAuthentication<?> getInstance(Object o, Class<? extends AbstractAuthentication> clazz) {
        AbstractAuthentication<?> abstractAuthentication;
        if ((abstractAuthentication = map.get(clazz)) != null) {
            return abstractAuthentication;
        }
        try {
            abstractAuthentication = clazz.newInstance();
            abstractAuthentication = abstractAuthentication.init(o);
            map.put(clazz, abstractAuthentication);
            return abstractAuthentication;
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("初始化鉴权器错误");
            return null;
        }
    }

}

