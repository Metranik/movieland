package com.art.movieland.dao.cache;

import com.art.movieland.dao.jdbc.JdbcGenreDao;
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

public class CacheGenreDaoTest {
    @Test
    public void testGetAll() {
        // Prepare
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        JdbcGenreDao jdbcGenreDao = new JdbcGenreDao(jdbcTemplate);
        CacheGenreDao cacheGenreDao = new CacheGenreDao(jdbcGenreDao);

        List<Genre> expectedGenres = new ArrayList<>();

        Genre genre1 = new Genre(1, "драма");
        expectedGenres.add(genre1);

        Genre genre2 = new Genre(2, "криминал");
        expectedGenres.add(genre2);

        // When
        when(jdbcTemplate.query(any(String.class), any(GenreRowMapper.class))).thenReturn(expectedGenres);

        // Then
        cacheGenreDao.populateCache();
        List<Genre> actualGenres = cacheGenreDao.getAll();

        assertEquals(expectedGenres.size(), actualGenres.size());

        assertEquals(expectedGenres.get(0), actualGenres.get(0));
        assertEquals(expectedGenres.get(1), actualGenres.get(1));
    }
}
