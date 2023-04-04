package com.csb.filter;

import com.csb.utils.Asset;
import com.csb.utils.AuthorityCheck;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@WebFilter(filterName = "isLoginFilter",urlPatterns = "/*")
public class CheckLoginFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/authority")){
            super.doFilter(request, response, chain);
        }
        else if (!Asset.isNull(request.getSession().getAttribute(AuthorityCheck.USER))) {
            super.doFilter(request, response, chain);
        }else {
            response.sendError(401);
        }
    }
}
