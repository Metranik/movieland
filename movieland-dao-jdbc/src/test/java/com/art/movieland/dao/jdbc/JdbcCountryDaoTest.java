package com.art.movieland.dao.jdbc;

import com.art.movieland.dao.CountryDao;
import com.art.movieland.dao.jdbc.mapper.CountryRowMapper;
import com.art.movieland.entity.Country;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JdbcCountryDaoTest {
    @Test
    public void testGetByMovie() {
        // Prepare
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        CountryDao countryDao = new JdbcCountryDao(jdbcTemplate);

        List<Country> expectedCountries = new ArrayList<>();

        Country country1 = new Country(1, "Италия");
        expectedCountries.add(country1);

        Country country2 = new Country(4, "США");
        expectedCountries.add(country2);

        // When
        when(jdbcTemplate.query(any(String.class),any(CountryRowMapper.class),anyInt())).thenReturn(expectedCountries);

        // Then
        List<Country> actualCountries = countryDao.getByMovie(1);

        assertEquals(expectedCountries.size(), actualCountries.size());

        assertEquals(expectedCountries.get(0),actualCountries.get(0));
        assertEquals(expectedCountries.get(1),actualCountries.get(1));
    }

    @Test
    public void testGetAll() {
        // Prepare
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        CountryDao countryDao = new JdbcCountryDao(jdbcTemplate);

        List<Country> expectedCountries = new ArrayList<>();

        Country country1 = new Country(1, "Италия");
        expectedCountries.add(country1);
        Country country2 = new Country(2, "Япония");
        expectedCountries.add(country2);
        Country country3 = new Country(3, "Великобритания");
        expectedCountries.add(country3);
        Country country4 = new Country(4, "США");
        expectedCountries.add(country4);

        // When
        when(jdbcTemplate.query(any(String.class), any(CountryRowMapper.class))).thenReturn(expectedCountries);

        // Then
        List<Country> actualCountries = countryDao.getAll();

        assertEquals(expectedCountries, actualCountries);
    }
}
