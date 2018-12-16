package com.art.movieland.entity;

public class UserHolder {
    private static final ThreadLocal<User> threadLocalUser = new ThreadLocal<>();

    public static User getUser() {
        return threadLocalUser.get();
    }

    public static void setUser(User user) {
        threadLocalUser.set(user);
    }
}
