package com.art.movieland.controller.rest;

import com.art.movieland.entity.Movie;
import com.art.movieland.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    MovieController movieController;

    @Mock
    MovieService movieService;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @Test
    public void testGetAll() throws Exception {
        // Prepare
        Movie movie1 = new Movie();
        movie1.setId(1);
        movie1.setNameRussian("Побег из Шоушенка");
        movie1.setNameNative("The Shawshank Redemption");
        movie1.setYearOfRelease(1994);
        movie1.setRating(8.9);
        movie1.setPrice(123.45);
        movie1.setPicturePath("url1");

        Movie movie2 = new Movie();
        movie2.setId(2);
        movie2.setNameRussian("Зеленая миля");
        movie2.setNameNative("The Green Mile");
        movie2.setYearOfRelease(1999);
        movie2.setRating(8.9);
        movie2.setPrice(134.67);
        movie2.setPicturePath("url2");

        // When
        when(movieService.getAll()).thenReturn(Arrays.asList(movie1, movie2));

        // Then
        mockMvc.perform(get("/v1/movie"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[0].nameRussian", equalTo("Побег из Шоушенка")))
                .andExpect(jsonPath("$[0].nameNative", equalTo("The Shawshank Redemption")))
                .andExpect(jsonPath("$[0].yearOfRelease", equalTo(1994)))
                .andExpect(jsonPath("$[0].rating", equalTo(8.9)))
                .andExpect(jsonPath("$[0].price", equalTo(123.45)))
                .andExpect(jsonPath("$[0].picturePath", equalTo("url1")))
                .andExpect(jsonPath("$[1].id", equalTo(2)))
                .andExpect(jsonPath("$[1].nameRussian", equalTo("Зеленая миля")))
                .andExpect(jsonPath("$[1].nameNative", equalTo("The Green Mile")))
                .andExpect(jsonPath("$[1].yearOfRelease", equalTo(1999)))
                .andExpect(jsonPath("$[1].rating", equalTo(8.9)))
                .andExpect(jsonPath("$[1].price", equalTo(134.67)))
                .andExpect(jsonPath("$[1].picturePath", equalTo("url2")));
    }
}
