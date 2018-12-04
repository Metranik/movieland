package com.art.movieland.service;

import com.art.movieland.dao.ReviewDao;
import com.art.movieland.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultReviewService implements ReviewService {
    private ReviewDao reviewDao;

    @Autowired
    public DefaultReviewService(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @Override
    public List<Review> getByMovie(int movieId) {
        return reviewDao.getByMovie(movieId);
    }
}
