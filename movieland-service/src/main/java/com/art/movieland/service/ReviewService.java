package com.art.movieland.service;

import com.art.movieland.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getByMovie(int movieId);
}
