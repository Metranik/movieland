package com.art.movieland.controller.rest;

import com.art.movieland.entity.UserCredentials;
import com.art.movieland.entity.UserToken;
import com.art.movieland.service.SecurityService;
import com.art.movieland.service.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
    private SecurityService securityService;

    @Autowired
    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PutMapping(value = "/v1/login",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserToken login(UserCredentials userCredentials) {
        UserToken securityToken = securityService.login(userCredentials);
        return securityToken;
    }

    @DeleteMapping(value = "/v1/logout")
    public String logout(@RequestParam(value = "uuid") String uuid) {
        return securityService.logout(uuid);
    }
}
