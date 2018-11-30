package com.art.movieland.entity;

import lombok.Data;

import java.util.Objects;

@Data
@SuppressWarnings("unused")
public class Movie {
    private int id;
    private String nameRussian;
    private String nameNative;
    private int yearOfRelease;
    private double rating;
    private double price;
    private String description;
    private String picturePath;
}
