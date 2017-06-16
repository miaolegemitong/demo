package com.miaolegemitong.demo.springboot.filter;

/**
 * Created by miaolegemitong on 2017/6/16.
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        LOGGER.info("httpRequest.getRequestURI():" + httpRequest.getRequestURI());
        LOGGER.info("httpRequest.getRequestURL().toString():" + httpRequest.getRequestURL().toString());


        HttpSession session = httpRequest.getSession();
        LOGGER.info("session id:" + session.getId());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
