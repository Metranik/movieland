package com.art.movieland.dao.jdbc;

import com.art.movieland.dao.MovieDao;
import com.art.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.art.movieland.entity.Movie;
import com.art.movieland.entity.MovieParam;
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
        MovieParam movieParam = mock(MovieParam.class);

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
        List<Movie> actualMovies = movieDao.getAll(movieParam);

        assertEquals(expectedMovies.size(), actualMovies.size());

        assertEquals(expectedMovies.get(0), actualMovies.get(0));
        assertEquals(expectedMovies.get(1), actualMovies.get(1));
    }

    @Test
    public void testGetRandom() {
        // Prepare
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        MovieDao movieDao = new JdbcMovieDao(jdbcTemplate);
        MovieParam movieParam = mock(MovieParam.class);

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

        Movie movie3 = new Movie();
        movie3.setId(3);
        movie3.setNameRussian("Форрест Гамп");
        movie3.setNameNative("Forrest Gump");
        movie3.setYearOfRelease(1994);
        movie3.setRating(8.6);
        movie3.setPrice(200.6);
        movie3.setPicturePath("url3");
        expectedMovies.add(movie3);

        Movie movie5 = new Movie();
        movie5.setId(5);
        movie5.setNameRussian("1+1");
        movie5.setNameNative("Intouchables");
        movie5.setYearOfRelease(2011);
        movie5.setRating(8.3);
        movie5.setPrice(120.0);
        movie5.setPicturePath("url5");
        expectedMovies.add(movie5);

        // When
        when(jdbcTemplate.query(any(String.class), any(MovieRowMapper.class))).thenReturn(expectedMovies);

        // Then
        List<Movie> actualMovies = movieDao.getAll(movieParam);

        assertEquals(expectedMovies.size(), actualMovies.size());

        assertEquals(expectedMovies.get(0), actualMovies.get(0));
        assertEquals(expectedMovies.get(1), actualMovies.get(1));
        assertEquals(expectedMovies.get(2), actualMovies.get(2));
    }

    @Test
    public void testGetByGenre() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        MovieDao movieDao = new JdbcMovieDao(jdbcTemplate);
        MovieParam movieParam = mock(MovieParam.class);

        List<Movie> expectedMovies = new ArrayList<>();

        Movie movie1 = new Movie();
        movie1.setId(1);
        movie1.setNameRussian("Побег из Шоушенка");
        movie1.setNameNative("The Shawshank Redemption");
        movie1.setYearOfRelease(1994);
        movie1.setRating(8.9);
        movie1.setPrice(123.45);
        movie1.setPicturePath("path1");
        expectedMovies.add(movie1);

        Movie movie2 = new Movie();
        movie2.setId(2);
        movie2.setNameRussian("Зеленая миля");
        movie2.setNameNative("The Green Mile");
        movie2.setYearOfRelease(1999);
        movie2.setRating(8.9);
        movie2.setPrice(134.67);
        movie2.setPicturePath("path2");
        expectedMovies.add(movie2);

        // When
        when(jdbcTemplate.query(any(String.class), any(MovieRowMapper.class), any(Integer.class))).thenReturn(expectedMovies);

        // Then
        List<Movie> actualMovies = movieDao.getByGenre(1, movieParam);

        assertEquals(expectedMovies.size(), actualMovies.size());

        assertEquals(expectedMovies.get(0), actualMovies.get(0));
        assertEquals(expectedMovies.get(1), actualMovies.get(1));
    }

}
