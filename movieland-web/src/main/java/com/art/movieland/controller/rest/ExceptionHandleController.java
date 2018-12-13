package com.art.movieland.controller.rest;

import com.art.movieland.service.exception.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandleController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST,
            reason = "AuthenticationException")
    void handleAuthenticationException(Exception e) {
        logger.error("handleAuthenticationException: ", e);
    }
}
