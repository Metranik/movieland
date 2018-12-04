package com.art.movieland.dao.jdbc;

import com.art.movieland.dao.ReviewDao;
import com.art.movieland.dao.jdbc.mapper.ReviewRowMapper;
import com.art.movieland.entity.Review;
import com.art.movieland.entity.User;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JdbcReviewDaoTest {
    @Test
    public void testGetReviewesByMovieId() {
        // Prepare
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        ReviewDao reviewDao = new JdbcReviewDao(jdbcTemplate);

        User user1 = new User();
        user1.setId(1);
        user1.setName("Name1");
        Review review1 = new Review();
        review1.setId(1);
        review1.setUser(user1);
        review1.setComment("Comment1");

        User user2 = new User();
        user1.setId(2);
        user1.setName("Name2");
        Review review2 = new Review();
        review1.setId(2);
        review1.setUser(user2);
        review1.setComment("Comment2");

        List<Review> expectedReviews = Arrays.asList(review1, review2);

        // When
        when(jdbcTemplate.query(any(String.class), any(ReviewRowMapper.class), any(Integer.class))).thenReturn(Arrays.asList(review1, review2));

        // Then
        List<Review> actualReviews = reviewDao.getByMovie(1);

        assertEquals(expectedReviews, actualReviews);
    }
}
