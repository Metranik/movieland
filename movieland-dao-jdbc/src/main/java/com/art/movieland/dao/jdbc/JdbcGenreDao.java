package com.art.movieland.dao.jdbc;

import com.art.movieland.dao.GenreDao;
import com.art.movieland.dao.jdbc.mapper.GenreRowMapper;
import com.art.movieland.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcGenreDao implements GenreDao {
    private static final String GET_ALL_GENRES = "SELECT g.id, g.name FROM genre g";
    private static final GenreRowMapper GENRE_ROW_MAPPER = new GenreRowMapper();

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcGenreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Genre> getAll() {
        return jdbcTemplate.query(GET_ALL_GENRES, GENRE_ROW_MAPPER);
    }
}
