package com.art.movieland.dao.jdbc;

import com.art.movieland.dao.MovieDao;
import com.art.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.art.movieland.entity.Movie;
import com.art.movieland.entity.MovieParam;
import com.art.movieland.entity.MovieQueryField;
import com.art.movieland.entity.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcMovieDao implements MovieDao {
    private static final String GET_ALL_MOVIES = "SELECT m.id, m.nameRussian, m.nameNative, m.yearOfRelease, m.rating, m.price, m.description, p.picturePath " +
            "FROM movie m " +
            "JOIN poster p ON m.id = p.movieId";
    private static final String GET_RANDOM_MOVIES_POSTGRESQL = "SELECT m.id, m.nameRussian, m.nameNative, m.yearOfRelease, m.rating, m.price, m.description, p.picturePath " +
            "FROM movie m TABLESAMPLE BERNOULLI(50) " +
            "JOIN poster p ON m.id = p.movieId LIMIT ?";
    private static final String GET_RANDOM_MOVIES = "SELECT m.id, m.nameRussian, m.nameNative, m.yearOfRelease, m.rating, m.price, m.description, p.picturePath " +
            "FROM movie m " +
            "JOIN poster p ON m.id = p.movieId " +
            "WHERE RANDOM() < 0.5 LIMIT ?";
    private static final String GET_MOVIES_BY_GENRE = "SELECT m.id, m.nameRussian, m.nameNative, m.yearOfRelease, m.rating, m.price, m.description, p.picturePath " +
            "FROM movie m " +
            "JOIN poster p ON m.id = p.movieId " +
            "JOIN movie_to_genre mg ON m.id = mg.movieId " +
            "WHERE mg.genreId = ?";
    private static final String GET_MOVIE_BY_ID = "SELECT m.id, m.nameRussian, m.nameNative, m.yearOfRelease, m.rating, m.price, m.description, p.picturePath " +
            "FROM movie m " +
            "JOIN poster p ON m.id = p.movieId " +
            "WHERE m.id = ?";
    private static final MovieRowMapper MOVIE_ROW_MAPPER = new MovieRowMapper();

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcMovieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> getAll(MovieParam movieParam) {
        logger.debug("MovieParam {}", movieParam);
        return jdbcTemplate.query(buildQuery(GET_ALL_MOVIES, movieParam), MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> getRandom(int count) {
        return jdbcTemplate.query(GET_RANDOM_MOVIES, MOVIE_ROW_MAPPER, count);
    }

    @Override
    public List<Movie> getByGenre(int genreId, MovieParam movieParam) {
        return jdbcTemplate.query(buildQuery(GET_MOVIES_BY_GENRE, movieParam), MOVIE_ROW_MAPPER, genreId);
    }

    @Override
    public Movie getById(int id) {
        return jdbcTemplate.queryForObject(GET_MOVIE_BY_ID, MOVIE_ROW_MAPPER, id);
    }

    private String buildQuery(String sql, MovieParam movieParam) {
        StringBuilder stringBuilder = new StringBuilder(sql);

        Map<MovieQueryField, SortOrder> sortMap = movieParam.getSortMap();
        if (sortMap == null || !sortMap.keySet().iterator().hasNext()) {
            return stringBuilder.toString();
        }

        stringBuilder.append(" ORDER BY ");
        for (Iterator<Map.Entry<MovieQueryField, SortOrder>> i = sortMap.entrySet().iterator(); i.hasNext(); ) {
            Map.Entry entry = i.next();
            stringBuilder.append(entry.getKey()).append(" ").append(entry.getValue());
            if (i.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        logger.debug("Movie buildQuery: {}", stringBuilder.toString());
        return stringBuilder.toString();
    }
}
