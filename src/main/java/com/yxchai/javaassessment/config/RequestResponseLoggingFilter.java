package com.yxchai.javaassessment.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RequestResponseLoggingFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filter) throws IOException, ServletException{
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        log.info("Incoming HttpRequest Method:{}, URI:{}, Remote IP:{}", httpServletRequest.getMethod(), httpServletRequest.getRequestURI(), httpServletRequest.getRemoteHost());

        filter.doFilter(httpServletRequest, httpServletResponse);

        log.info("Outgoing HttpResponse Status:{}", httpServletResponse.getStatus());
    }
}
