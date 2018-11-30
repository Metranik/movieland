package com.art.movieland.entity;

import lombok.Data;

import java.util.List;

//@Data
@SuppressWarnings("unused")
public class MovieFull {
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

    public MovieFull(Movie movie) {
        id = movie.getId();
        nameRussian = movie.getNameRussian();
        nameNative = movie.getNameNative();
        yearOfRelease = movie.getYearOfRelease();
        rating = movie.getRating();
        price = movie.getPrice();
        description = movie.getDescription();
        picturePath = movie.getPicturePath();
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "MovieFull{" +
                "id=" + id +
                ", nameRussian='" + nameRussian + '\'' +
                ", nameNative='" + nameNative + '\'' +
                ", yearOfRelease=" + yearOfRelease +
                ", rating=" + rating +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", picturePath='" + picturePath + '\'' +
                ", countries=" + countries +
                ", genres=" + genres +
                ", reviews=" + reviews +
                '}';
    }
}
