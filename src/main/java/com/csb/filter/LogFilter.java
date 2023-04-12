package com.csb.filter;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
@Order(1)
@WebFilter(filterName = "LogFilter", urlPatterns = "/*")
public class LogFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(this.getClass().getSimpleName());
        StringBuffer url = request.getRequestURL();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Object json = JSON.toJSON(parameterMap);
        url.append(":\n").append(json);
        log.info(url.toString());
        super.doFilter(request, response, chain);
    }
}
