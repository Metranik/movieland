package com.art.movieland.service;

import com.art.movieland.dao.ReviewDao;
import com.art.movieland.entity.Review;
import com.art.movieland.entity.User;
import com.art.movieland.service.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultReviewService implements ReviewService {
    private ReviewDao reviewDao;
    private SecurityService securityService;

    @Autowired
    public DefaultReviewService(ReviewDao reviewDao,
                                SecurityService securityService) {
        this.reviewDao = reviewDao;
        this.securityService = securityService;
    }

    @Override
    public List<Review> getByMovie(int movieId) {
        return reviewDao.getByMovie(movieId);
    }

    @Override
    public Review addMovieReview(Review review) {
        return reviewDao.addMovieReview(review);
    }

}
