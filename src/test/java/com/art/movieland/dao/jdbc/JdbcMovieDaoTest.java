package com.art.movieland.dao.jdbc;

import com.art.movieland.dao.MovieDao;
import com.art.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.art.movieland.entity.Movie;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;



import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JdbcMovieDaoTest {

    @Test
    public void testGetAll() {
        // Prepare
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        MovieDao movieDao = new JdbcMovieDao(jdbcTemplate);

        List<Movie> expectedMovies = new ArrayList<>();

        Movie movie1 = new Movie();
        movie1.setId(1);
        movie1.setNameRussian("Побег из Шоушенка");
        movie1.setNameNative("The Shawshank Redemption");
        movie1.setYearOfRelease(1994);
        movie1.setRating(8.9);
        movie1.setPrice(123.45);
        movie1.setPicturePath("url1");
        expectedMovies.add(movie1);

        Movie movie2 = new Movie();
        movie2.setId(2);
        movie2.setNameRussian("Зеленая миля");
        movie2.setNameNative("The Green Mile");
        movie2.setYearOfRelease(1999);
        movie2.setRating(8.9);
        movie2.setPrice(134.67);
        movie2.setPicturePath("url2");
        expectedMovies.add(movie2);

        // When
        when(jdbcTemplate.query(any(String.class), any(MovieRowMapper.class))).thenReturn(expectedMovies);

        // Then
        List<Movie> actualMovies = movieDao.getAll();

        assertEquals(2, actualMovies.size());

        Movie actualMovie1 = actualMovies.get(0);
        Movie expectedMovie1 = expectedMovies.get(0);
        assertEquals(expectedMovie1.getId(),actualMovie1.getId());
        assertEquals(expectedMovie1.getNameRussian(),actualMovie1.getNameRussian());
        assertEquals(expectedMovie1.getNameNative(),actualMovie1.getNameNative());
        assertEquals(expectedMovie1.getYearOfRelease(),actualMovie1.getYearOfRelease());
        assertEquals(expectedMovie1.getRating(),actualMovie1.getRating(),0d);
        assertEquals(expectedMovie1.getPrice(),actualMovie1.getPrice(),0d);
        assertEquals(expectedMovie1.getPicturePath(),actualMovie1.getPicturePath());

        Movie actualMovie2 = actualMovies.get(1);
        Movie expectedMovie2 = expectedMovies.get(1);
        assertEquals(expectedMovie2.getId(),actualMovie2.getId());
        assertEquals(expectedMovie2.getNameRussian(),actualMovie2.getNameRussian());
        assertEquals(expectedMovie2.getNameNative(),actualMovie2.getNameNative());
        assertEquals(expectedMovie2.getYearOfRelease(),actualMovie2.getYearOfRelease());
        assertEquals(expectedMovie2.getRating(),actualMovie2.getRating(),0d);
        assertEquals(expectedMovie2.getPrice(),actualMovie2.getPrice(),0d);
        assertEquals(expectedMovie2.getPicturePath(),actualMovie2.getPicturePath());
    }
}
