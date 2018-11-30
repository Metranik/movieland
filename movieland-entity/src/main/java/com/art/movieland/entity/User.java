package com.art.movieland.entity;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class User {
    private int id;
    private String name;
    private String email;
    private String nick;
}
