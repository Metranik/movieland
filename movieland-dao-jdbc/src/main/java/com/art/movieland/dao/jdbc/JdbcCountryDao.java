package com.art.movieland.dao.jdbc;

import com.art.movieland.dao.CountryDao;
import com.art.movieland.dao.jdbc.mapper.CountryRowMapper;
import com.art.movieland.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCountryDao implements CountryDao {
    private static final String GET_COUNTRIES_BY_MOVIE = "SELECT c.id, c.name " +
            "FROM country c " +
            "JOIN movie_to_country mc ON c.id = mc.countryId " +
            "WHERE mc.movieId = ?";
    private static final String GET_ALL_COUNTRIES = "SELECT c.id, c.name FROM country c";
    private static final CountryRowMapper COUNTRY_ROW_MAPPER = new CountryRowMapper();

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcCountryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Country> getByMovie(int movieId) {
        return jdbcTemplate.query(GET_COUNTRIES_BY_MOVIE, COUNTRY_ROW_MAPPER, movieId);
    }

    @Override
    public List<Country> getAll() {
        return jdbcTemplate.query(GET_ALL_COUNTRIES, COUNTRY_ROW_MAPPER);
    }
}

