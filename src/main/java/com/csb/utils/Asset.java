package com.csb.utils;

import com.csb.vo.MSG;

public class Asset {
    public static boolean isNull(Object o) {
        return null == o;
    }

    public static boolean isPosNum(String s) {
        try {
            long l = Long.parseLong(s);
            return l >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
