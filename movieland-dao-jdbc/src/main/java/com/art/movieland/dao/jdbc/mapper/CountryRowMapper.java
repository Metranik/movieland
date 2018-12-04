package com.art.movieland.dao.jdbc.mapper;

import com.art.movieland.entity.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryRowMapper implements RowMapper<Country> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Country mapRow(ResultSet resultSet, int i) throws SQLException {
        Country country = new Country(
                resultSet.getInt("id"),
                resultSet.getString("name"));

        logger.trace("Country {}", country);

        return country;
    }
}
