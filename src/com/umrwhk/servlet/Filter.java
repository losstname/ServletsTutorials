package com.umrwhk.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by losstname on 5/22/17.
 */
public class Filter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String testParam = filterConfig.getInitParameter("Test-Param");
        System.out.println("Test Param: "+testParam);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String ipAddr = servletRequest.getRemoteAddr();
        System.out.println("IP "+ipAddr+", Time"+new Date().toString());
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
