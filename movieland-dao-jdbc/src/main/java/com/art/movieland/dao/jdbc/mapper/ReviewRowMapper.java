package com.art.movieland.dao.jdbc.mapper;

import com.art.movieland.entity.Review;
import com.art.movieland.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewRowMapper implements RowMapper<Review> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Review mapRow(ResultSet resultSet, int i) throws SQLException {
        Review review = new Review();
        review.setId(resultSet.getInt("id"));
        review.setMovieId(resultSet.getInt("movieId"));
        review.setText(resultSet.getString("text"));
        User user = new User();
        user.setId(resultSet.getInt("userId"));
        user.setName(resultSet.getString("name"));
        review.setUser(user);

        logger.trace("Review {}", review);

        return review;
    }
}
