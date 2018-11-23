package com.art.movieland.dao.jdbc;

import com.art.movieland.dao.MovieDao;
import com.art.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.art.movieland.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class JdbcMovieDao implements MovieDao {
    private static final String GET_ALL_MOVIES = "SELECT m.id, m.nameRussian, m.nameNative, m.yearOfRelease, m.rating, m.price, p.picturePath FROM movie m JOIN poster p ON m.id = p.movieId";
    private static final String GET_RANDOM_MOVIES = "SELECT m.id, m.nameRussian, m.nameNative, m.yearOfRelease, m.rating, m.price, p.picturePath FROM movie m TABLESAMPLE SYSTEM(50) JOIN poster p ON m.id = p.movieId LIMIT %d";
    private static final MovieRowMapper MOVIE_ROW_MAPPER = new MovieRowMapper();

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcMovieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> getAll() {
        List<Movie> movies = jdbcTemplate.query(GET_ALL_MOVIES, MOVIE_ROW_MAPPER);
        logger.trace("All Movies {}", movies);
        return movies;
    }

    @Override
    public List<Movie> getRandom(int count) {
        String query = String.format(GET_RANDOM_MOVIES, count);
        List<Movie> movies = jdbcTemplate.query(query, MOVIE_ROW_MAPPER);
        logger.trace("Random Movies {}", movies);
        return movies;
    }
}