package com.art.movieland.entity;

import lombok.Data;

@Data
public class Review {
    private int id;
    private int movieId;
    private int userId;
    private String comment;
}
