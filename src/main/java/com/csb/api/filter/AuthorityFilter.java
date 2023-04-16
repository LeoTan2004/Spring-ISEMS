package com.csb.api.filter;

import com.csb.utils.Assert;
import com.csb.utils.SessionUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
@Order(1 << 2)
@Component
public class AuthorityFilter extends HttpFilter {
    private static final String[] PUBLIC_URL = {"/authority"};

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println(this.getClass().getSimpleName());
        if (Assert.isNull(SessionUtils.getCurUser(request.getSession()))) {
            String requestURI = request.getRequestURI();
            for (String s : PUBLIC_URL) {
                if (requestURI.startsWith(s)) {
                    super.doFilter(request, response, chain);
                    return;
                }
            }
            response.sendError(406);
        }
        super.doFilter(request, response, chain);
    }
}
