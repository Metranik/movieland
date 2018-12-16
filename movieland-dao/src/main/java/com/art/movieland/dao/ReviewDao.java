package com.art.movieland.dao;

import com.art.movieland.entity.Review;
import com.art.movieland.entity.User;

import java.util.List;

public interface ReviewDao {
    List<Review> getByMovie(int movieId);
    Review addMovieReview(Review review);
}
