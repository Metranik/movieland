package com.art.movieland.dao;

import com.art.movieland.entity.User;

import java.util.Optional;

public interface UserDao {
    User getById(int id);
    Optional<User> getByEmail(String email);
}
