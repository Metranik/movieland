package com.art.movieland.controller.dto;

import com.art.movieland.entity.Country;
import com.art.movieland.entity.Genre;
import com.art.movieland.entity.Review;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class MovieDto {
    @JsonView({Views.AllMovie.class,
            Views.DetailedMovie.class})
    private int id;

    @JsonView({Views.AllMovie.class,
            Views.DetailedMovie.class})
    private String nameRussian;

    @JsonView({Views.AllMovie.class,
            Views.DetailedMovie.class})
    private String nameNative;

    @JsonView({Views.AllMovie.class,
            Views.DetailedMovie.class})
    private int yearOfRelease;

    @JsonView({Views.AllMovie.class,
            Views.DetailedMovie.class})
    private double rating;

    @JsonView({Views.AllMovie.class,
            Views.DetailedMovie.class})
    private double price;

    @JsonView(Views.DetailedMovie.class)
    private String description;

    @JsonView({Views.AllMovie.class,
            Views.DetailedMovie.class})
    private String picturePath;

    @JsonView({Views.DetailedMovie.class})
    private List<Country> countries;

    @JsonView({Views.DetailedMovie.class})
    private List<Genre> genres;

    @JsonView({Views.DetailedMovie.class})
    private List<Review> reviews;
}
