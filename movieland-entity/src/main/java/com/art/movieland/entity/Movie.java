package com.art.movieland.entity;

import lombok.Data;

import java.util.List;

@Data
public class Movie {
    private int id;
    private String nameRussian;
    private String nameNative;
    private int yearOfRelease;
    private double rating;
    private double price;
    private String description;
    private String picturePath;
    private List<Country> countries;
    private List<Genre> genres;
    private List<Review> reviews;

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
