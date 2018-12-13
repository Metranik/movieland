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
    public Review addMovieReview(Review review) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(ADD_MOVIE_REVIEW, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, review.getMovieId());
                preparedStatement.setInt(2, review.getUser().getId());
                preparedStatement.setString(3, review.getText());
                return preparedStatement;
            }
        }, keyHolder);

        int reviewId = keyHolder.getKey().intValue();
        review.setId(reviewId);
        return review;
    }

}
