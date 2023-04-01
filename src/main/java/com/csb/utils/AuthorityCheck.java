package com.csb.utils;

import com.csb.pojo.Team;
import com.csb.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Scope("session")
@NoArgsConstructor
public class AuthorityCheck {
    public static final String USER = "USER";
    public static final String PIC_CODE = "CODE";

    public AuthorityCheck(HttpServletRequest request) {
        this.request = request;
    }

    @Autowired
    HttpServletRequest request;
    public boolean isLogin(){
        return Asset.isNull(this.getCurUser());
    }
    public boolean isAdmin(Team team){
        return Objects.equals(team.getTeamAdmin(), this.getCurUser().getUid());
    }

    public User getCurUser(){
        return (User) request.getSession().getAttribute(USER);
    }

    public void setCurUser(User user){
        request.getSession().setAttribute(USER,user);
    }

    public boolean checkPicCaptcha(String s){
        if (s == null) {
            return false;
        }
        Object attribute = request.getSession().getAttribute(this.PIC_CODE);
        if (attribute == null) {
            return false;
        }
        if (attribute.equals(s)){
            request.getSession().setAttribute(this.PIC_CODE,null);
            return true;
        }else{
            request.getSession().setAttribute(this.PIC_CODE,null);
            return false;
        }
    }
}
