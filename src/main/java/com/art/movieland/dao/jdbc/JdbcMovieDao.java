package com.art.movieland.dao.jdbc;

import com.art.movieland.dao.MovieDao;
import com.art.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.art.movieland.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcMovieDao implements MovieDao {
    private static final String GET_ALL_MOVIES = "SELECT m.id, m.nameRussian, m.nameNative, m.yearOfRelease, m.rating, m.price, p.picturePath FROM movie m JOIN poster p ON m.id = p.movieId";
    private static final String GET_RANDOM_MOVIES = "SELECT m.id, m.nameRussian, m.nameNative, m.yearOfRelease, m.rating, m.price, p.picturePath FROM movie m TABLESAMPLE BERNOULLI(50) JOIN poster p ON m.id = p.movieId LIMIT ?";
    private static final String GET_MOVIES_BY_GENRE = "SELECT m.id, m.nameRussian, m.nameNative, m.yearOfRelease, m.rating, m.price, p.picturePath " +
            "FROM movie m " +
            "JOIN poster p ON m.id = p.movieId " +
            "JOIN movie_to_genre mg ON m.id = mg.movieId " +
            "WHERE mg.genreId = ?";
    private static final MovieRowMapper MOVIE_ROW_MAPPER = new MovieRowMapper();

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcMovieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> getAll() {
        return jdbcTemplate.query(GET_ALL_MOVIES, MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> getRandom(int count) {
        return jdbcTemplate.query(GET_RANDOM_MOVIES, MOVIE_ROW_MAPPER, count);
    }

    @Override
    public List<Movie> getByGenre(int genreId) {
        return jdbcTemplate.query(GET_MOVIES_BY_GENRE, MOVIE_ROW_MAPPER, genreId);
    }

}
