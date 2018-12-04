package com.art.movieland.dao.jdbc;

import com.art.movieland.dao.ReviewDao;
import com.art.movieland.dao.jdbc.mapper.ReviewRowMapper;
import com.art.movieland.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcReviewDao implements ReviewDao {
    private static final String GET_REVIEWS_BY_MOVIE = "SELECT r.id, r.userId, r.comment, u.name " +
            "FROM review r " +
            "JOIN users u ON u.id = r.userId " +
            "WHERE movieId = ?";
    private static final ReviewRowMapper REVIEW_ROW_MAPPER = new ReviewRowMapper();

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcReviewDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Review> getByMovie(int movieId) {
        return jdbcTemplate.query(GET_REVIEWS_BY_MOVIE, REVIEW_ROW_MAPPER, movieId);
    }
}
