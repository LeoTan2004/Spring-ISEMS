//package com.csb.filter;
//
//import com.csb.utils.Asset;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//@Slf4j
//@Order(1<<2)
//@WebFilter(filterName = "ParamFilter", urlPatterns = "/*")
//public class BasicParamFilter extends HttpFilter {
//    public static final String[] strings = {"offset", "uid", "tid", "mid", "rid","username"};
//
//    @Override
//    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println(this.getClass().getSimpleName());
//        for (String string : strings) {
//            if (!checkPos(request, string)) {
//                response.sendError(406);
//                return;
//            }
//        }
//        super.doFilter(request, response, chain);
//    }
//
//    private static boolean checkPos(HttpServletRequest request, String paramName) {
//        if (request.getParameterMap().containsKey(paramName)){
//            System.out.println(request.getParameter(paramName));
//            return !Asset.isNull(request.getParameter(paramName));
//        }
//        return true;
////        return !(request.getParameterMap().containsKey(paramName) && (!Asset.isPosNum(request.getParameter(paramName))));
//    }
//}
