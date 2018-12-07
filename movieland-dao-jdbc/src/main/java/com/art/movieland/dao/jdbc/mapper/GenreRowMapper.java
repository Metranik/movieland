package com.art.movieland.dao.jdbc.mapper;

import com.art.movieland.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreRowMapper implements RowMapper<Genre> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {

        Genre genre = new Genre(
                resultSet.getInt("id"),
                resultSet.getString("name"));

        logger.trace("Genre {}", genre);

        return genre;
    }
}

