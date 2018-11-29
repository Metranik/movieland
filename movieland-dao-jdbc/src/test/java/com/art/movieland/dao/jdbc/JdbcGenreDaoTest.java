package com.art.movieland.dao.jdbc;

import com.art.movieland.dao.jdbc.mapper.GenreRowMapper;
import com.art.movieland.entity.Genre;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JdbcGenreDaoTest {

    @Test
    public void testGetAll() {
        // Prepare
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        JdbcGenreDao genreDao = new JdbcGenreDao(jdbcTemplate);

        List<Genre> expectedGenres = new ArrayList<>();

        Genre genre1 = new Genre(1,"драма");
        expectedGenres.add(genre1);

        Genre genre2 = new Genre(2,"криминал");
        expectedGenres.add(genre2);

        // When
        when(jdbcTemplate.query(any(String.class),any(GenreRowMapper.class))).thenReturn(expectedGenres);

        // Then
        List<Genre> actualGenres = genreDao.getAll();

        assertEquals(expectedGenres.size(), actualGenres.size());

        assertEquals(expectedGenres.get(0),actualGenres.get(0));
        assertEquals(expectedGenres.get(1),actualGenres.get(1));
    }

}
