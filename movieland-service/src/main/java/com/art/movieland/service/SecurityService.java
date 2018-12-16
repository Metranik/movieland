package com.art.movieland.service;

import com.art.movieland.entity.User;
import com.art.movieland.entity.UserCredentials;
import com.art.movieland.entity.UserToken;

import java.util.Optional;

public interface SecurityService {
    UserToken login(UserCredentials userCredentials);
    UserToken logout(String uuid);
    String getNameByUuid(String uuid);
    Optional<User> getUserByUuid(String uuid);
}
