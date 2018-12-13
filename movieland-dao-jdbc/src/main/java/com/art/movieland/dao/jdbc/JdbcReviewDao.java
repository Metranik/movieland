package com.art.movieland.dao.jdbc;

import com.art.movieland.dao.ReviewDao;
import com.art.movieland.dao.jdbc.mapper.ReviewRowMapper;
import com.art.movieland.entity.Review;
import com.art.movieland.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class JdbcReviewDao implements ReviewDao {
    private static final String GET_REVIEWS_BY_MOVIE = "SELECT r.id, r.movieId, r.userId, r.text, u.name " +
            "FROM review r " +
            "JOIN users u ON u.id = r.userId " +
            "WHERE movieId = ?";
    private static final String ADD_MOVIE_REVIEW = "INSERT INTO review (movieId, userId, text) VALUES(?, ?, ?)";
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

    @Override
    public Review addMovieReview(int movieId, User user, String text) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(ADD_MOVIE_REVIEW, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, movieId);
                preparedStatement.setInt(2, user.getId());
                preparedStatement.setString(3, text);
                return preparedStatement;
            }
        }, keyHolder);
        int reviewId = keyHolder.getKey().intValue();

        Review review = new Review();
        review.setId(reviewId);
        review.setMovieId(movieId);
        review.setUser(user);
        review.setText(text);
        return review;
    }

}
