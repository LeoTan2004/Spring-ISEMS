package com.csb.api.filter;

import com.alibaba.fastjson2.JSON;
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

@WebFilter(urlPatterns = "/*")
@Order(1)
@Slf4j
@Component
public class LogFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(this.getClass().getSimpleName());
        this.logRequest(request);
        super.doFilter(request, response, chain);
    }

    private void logRequest(HttpServletRequest request){
        String param = JSON.toJSONString(request.getParameterMap());
        String cookies = JSON.toJSONString(request.getCookies());
        String uri = request.getRequestURI();
        log.info("["+uri+"]("+cookies+"):\n"+"{"+param+"}");
    }
}
