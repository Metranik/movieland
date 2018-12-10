package com.art.movieland.service;

import com.art.movieland.entity.UserCredentials;
import com.art.movieland.entity.UserToken;

public interface SecurityService {
    UserToken login(UserCredentials userCredentials);
    String logout(String uuid);
}
