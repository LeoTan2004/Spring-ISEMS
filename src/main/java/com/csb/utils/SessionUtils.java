package com.csb.utils;

import com.csb.module.authority.UserDO;
import jakarta.servlet.http.HttpSession;

public class SessionUtils {
    private static final String CUR_USER = "CUR_USER";
    private static final String CUR_CAP = "CUR_CAP";

    public static UserDO getCurUser(HttpSession session) {
        if (Assert.isNull(session)) return null;
        Object attribute = session.getAttribute(CUR_USER);
        if (attribute instanceof UserDO userDO) {
            return userDO;
        }
        return null;
    }

    public static void setCurUser(HttpSession session, UserDO userDO) {
        if (Assert.isNull(session, userDO)) return;
        session.setAttribute(CUR_USER, userDO);
    }

    public static boolean authPicCaptcha(String cap, HttpSession session) {
        if (Assert.isEnpty(cap)) return false;
        if (Assert.isNull(session, session.getAttribute(CUR_CAP))) return false;
        String s = session.getAttribute(CUR_CAP).toString();
        if (Assert.isNull(s)) return false;
        session.removeAttribute(CUR_CAP);
        return (cap.equalsIgnoreCase(s));
    }

    public static void setCurCap(HttpSession session, String cap) {
        if (Assert.isNull(session, cap) || Assert.isEnpty(cap)) return;
        session.setAttribute(CUR_CAP, cap);
    }

    public static void clearSession(HttpSession session) {
        if (Assert.isNull(session)) return;
        session.invalidate();
    }



}
