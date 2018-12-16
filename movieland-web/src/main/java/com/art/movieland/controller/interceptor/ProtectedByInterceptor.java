package com.art.movieland.controller.interceptor;

import com.art.movieland.controller.annotation.ProtectedBy;
import com.art.movieland.entity.User;
import com.art.movieland.entity.UserHolder;
import com.art.movieland.entity.UserRole;
import com.art.movieland.service.SecurityService;
import com.art.movieland.service.exception.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProtectedByInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        ProtectedBy protectedBy = handlerMethod.getMethodAnnotation(ProtectedBy.class);
        if (protectedBy == null) {
            return true;
        }

        String uuid = request.getHeader("uuid");
        User user = securityService.getUserByUuid(uuid)
                .orElseThrow(() -> new AuthenticationException("UUID is incorrect"));
        UserHolder.setUser(user);

        UserRole userRole = UserRole.valueOf(user.getRole());
        logger.debug("UserRole: {}", userRole);

        UserRole[] protectedByRoles = protectedBy.value();
        for (UserRole protectedByRole : protectedByRoles) {
            if (protectedByRole == userRole) {
                return true;
            }
        }

        throw new AuthenticationException("Access forbidden for " + userRole);
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
