package com.art.movieland.dao;

import com.art.movieland.entity.User;

public interface UserDao {
    User getById(int id);
    User getByEmail(String email);
}
