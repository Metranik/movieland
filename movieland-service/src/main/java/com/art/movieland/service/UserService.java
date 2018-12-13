package com.art.movieland.service;

import com.art.movieland.entity.User;

import java.util.Optional;

public interface UserService {
    User getById(int id);
    Optional<User> getByEmail(String email);
}
