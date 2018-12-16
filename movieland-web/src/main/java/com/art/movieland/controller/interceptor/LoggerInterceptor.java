package com.art.movieland.controller.interceptor;

import com.art.movieland.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class LoggerInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ThreadLocal<Long> threadLocalStartTime = new ThreadLocal();
    private SecurityService securityService;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) {

        threadLocalStartTime.set(System.currentTimeMillis());

        String requestId = UUID.randomUUID().toString();
        MDC.put("requestId", requestId);

        String requestUuid = request.getHeader("uuid");
        String userName = securityService.getNameByUuid(requestUuid);
        MDC.put("userName", userName);

        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) {

        logger.info("Process time taken: {} ms", System.currentTimeMillis() - threadLocalStartTime.get());
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) {

        MDC.clear();
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
