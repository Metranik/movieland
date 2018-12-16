package com.art.movieland.service;

import com.art.movieland.entity.Review;
import com.art.movieland.entity.User;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<Review> getByMovie(int movieId);
    Review addMovieReview(Review review);
}
