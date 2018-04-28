package com.support.finance.security.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Ravinder Rathi on 6/9/17.
 */
public class XSSFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String path = ((HttpServletRequest) servletRequest).getRequestURI();
        if (path.startsWith("/resources/") || path.startsWith("/resources/cmfy/")) {
            filterChain.doFilter(servletRequest, servletResponse); // Just continue chain.
        } else {
            filterChain.doFilter(new XSSRequestWrapper((HttpServletRequest) servletRequest), servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
