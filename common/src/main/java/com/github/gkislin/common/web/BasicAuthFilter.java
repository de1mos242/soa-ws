package com.github.gkislin.common.web;

import javax.servlet.*;
import java.io.IOException;

/**
 * User: gkislin
 * Date: 17.06.2014
 */
abstract public class BasicAuthFilter implements Filter {
    private String authHeader;

    public void setAuthHeader(String authHeader) {
        this.authHeader = authHeader;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if (ServletUtil.checkBasicAuth(req, resp, authHeader)) {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void destroy() {
    }
}
