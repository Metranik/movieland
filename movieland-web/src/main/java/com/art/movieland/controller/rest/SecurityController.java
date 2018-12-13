package com.art.movieland.controller.rest;

import com.art.movieland.entity.UserCredentials;
import com.art.movieland.entity.UserToken;
import com.art.movieland.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class SecurityController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private SecurityService securityService;

    @Autowired
    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PutMapping(value = "/login",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserToken login(@RequestBody UserCredentials userCredentials) {
        UserToken securityToken = securityService.login(userCredentials);
        logger.info("Login: {}", securityToken);
        return securityToken;
    }

    @DeleteMapping(value = "/logout",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserToken logout(@RequestHeader(value = "uuid") String uuid) {
        UserToken userToken = securityService.logout(uuid);
        logger.info("Logout: ", userToken);
        return userToken;
    }
}
