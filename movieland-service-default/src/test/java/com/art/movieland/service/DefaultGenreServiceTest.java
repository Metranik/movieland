package com.art.movieland.service;

import com.art.movieland.entity.Genre;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultGenreServiceTest {

    @Test
    public void testGetAll() {
        // Prepare
        GenreService genreService = mock(GenreService.class);

        List<Genre> expectedGenres = new ArrayList<>();

        Genre genre1 = new Genre(1, "драма");
        expectedGenres.add(genre1);
        Genre genre2 = new Genre(2, "криминал");
        expectedGenres.add(genre2);

        // When
        when(genreService.getAll()).thenReturn(expectedGenres);

        // Then
        List<Genre> actualGenres = genreService.getAll();

        assertEquals(expectedGenres.size(), actualGenres.size());

        assertEquals(expectedGenres.get(0), actualGenres.get(0));
        assertEquals(expectedGenres.get(1), actualGenres.get(1));
    }

}