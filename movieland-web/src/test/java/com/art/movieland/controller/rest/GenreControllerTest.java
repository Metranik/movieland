package com.art.movieland.controller.rest;

import com.art.movieland.entity.Genre;
import com.art.movieland.service.GenreService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class GenreControllerTest {
    @InjectMocks
    GenreController genreController;
    @Mock
    GenreService genreService;
    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(genreController).build();
    }

    @Test
    public void testGetAll() throws Exception {
        // Prepare
        Genre genre1 = new Genre(1, "драма");
        Genre genre2 = new Genre(2, "криминал");

        // When
        when(genreController.getAll()).thenReturn(Arrays.asList(genre1, genre2));

        // Then
        mockMvc.perform(get("/v1/genre"))
                .andExpect(
                        matchAll(
                                status().isOk(),
                                content().contentType(MediaType.APPLICATION_JSON_UTF8),
                                jsonPath("$", Matchers.hasSize(2)),
                                jsonPath("$[0].id", Matchers.equalTo(1)),
                                jsonPath("$[0].name", Matchers.equalTo("драма")),
                                jsonPath("$[1].id", Matchers.equalTo(2)),
                                jsonPath("$[1].name", Matchers.equalTo("криминал"))
                        )
                );
    }
}
