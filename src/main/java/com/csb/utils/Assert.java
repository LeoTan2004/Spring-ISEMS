package com.csb.utils;

public class Assert {
    public static boolean isNull(Object... args){
        for (Object arg : args) {
            if (null == arg)return true;
        }
        return false;
    }

    public static boolean isEnpty(String... s) {
        for (String str : s) {
            if (null == str || "".equals(str.trim())) {
                return true;
            }
        }
        return false;
    }
}
