package com.art.movieland.controller.rest;

import com.art.movieland.entity.Movie;
import com.art.movieland.service.MovieService;
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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
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
        when(movieController.getAll()).thenReturn(Arrays.asList(movie1, movie2));

        // Then
        mockMvc.perform(get("/v1/movie"))
                .andExpect(
                        matchAll(
                                status().isOk(),
                                content().contentType(MediaType.APPLICATION_JSON_UTF8),
                                jsonPath("$", hasSize(2)),
                                jsonPath("$[0].id", equalTo(1)),
                                jsonPath("$[0].nameRussian", equalTo("Побег из Шоушенка")),
                                jsonPath("$[0].nameNative", equalTo("The Shawshank Redemption")),
                                jsonPath("$[0].yearOfRelease", equalTo(1994)),
                                jsonPath("$[0].rating", equalTo(8.9)),
                                jsonPath("$[0].price", equalTo(123.45)),
                                jsonPath("$[0].picturePath", equalTo("url1")),
                                jsonPath("$[1].id", equalTo(2)),
                                jsonPath("$[1].nameRussian", equalTo("Зеленая миля")),
                                jsonPath("$[1].nameNative", equalTo("The Green Mile")),
                                jsonPath("$[1].yearOfRelease", equalTo(1999)),
                                jsonPath("$[1].rating", equalTo(8.9)),
                                jsonPath("$[1].price", equalTo(134.67)),
                                jsonPath("$[1].picturePath", equalTo("url2"))
                        )
                );
    }

    @Test
    public void testGetRandom() throws Exception {
        // Prepare
        Movie movie1 = new Movie();
        movie1.setId(1);
        movie1.setNameRussian("Побег из Шоушенка");
        movie1.setNameNative("The Shawshank Redemption");
        movie1.setYearOfRelease(1994);
        movie1.setRating(8.9);
        movie1.setPrice(123.45);
        movie1.setPicturePath("url1");

        Movie movie3 = new Movie();
        movie3.setId(3);
        movie3.setNameRussian("Форрест Гамп");
        movie3.setNameNative("Forrest Gump");
        movie3.setYearOfRelease(1994);
        movie3.setRating(8.6);
        movie3.setPrice(200.6);
        movie3.setPicturePath("url3");

        Movie movie5 = new Movie();
        movie5.setId(5);
        movie5.setNameRussian("1+1");
        movie5.setNameNative("Intouchables");
        movie5.setYearOfRelease(2011);
        movie5.setRating(8.3);
        movie5.setPrice(120.0);
        movie5.setPicturePath("url5");

        // When
        when(movieController.getRandom()).thenReturn(Arrays.asList(movie1, movie3, movie5));

        // Then
        mockMvc.perform(get("/v1/movie/random"))
                .andExpect(
                        matchAll(
                                status().isOk(),
                                content().contentType(MediaType.APPLICATION_JSON_UTF8),
                                jsonPath("$", hasSize(3)),
                                jsonPath("$[0].id", equalTo(1)),
                                jsonPath("$[0].nameRussian", equalTo("Побег из Шоушенка")),
                                jsonPath("$[0].nameNative", equalTo("The Shawshank Redemption")),
                                jsonPath("$[0].yearOfRelease", equalTo(1994)),
                                jsonPath("$[0].rating", equalTo(8.9)),
                                jsonPath("$[0].price", equalTo(123.45)),
                                jsonPath("$[0].picturePath", equalTo("url1")),
                                jsonPath("$[1].id", equalTo(3)),
                                jsonPath("$[1].nameRussian", equalTo("Форрест Гамп")),
                                jsonPath("$[1].nameNative", equalTo("Forrest Gump")),
                                jsonPath("$[1].yearOfRelease", equalTo(1994)),
                                jsonPath("$[1].rating", equalTo(8.6)),
                                jsonPath("$[1].price", equalTo(200.6)),
                                jsonPath("$[1].picturePath", equalTo("url3")),
                                jsonPath("$[2].id", equalTo(5)),
                                jsonPath("$[2].nameRussian", equalTo("1+1")),
                                jsonPath("$[2].nameNative", equalTo("Intouchables")),
                                jsonPath("$[2].yearOfRelease", equalTo(2011)),
                                jsonPath("$[2].rating", equalTo(8.3)),
                                jsonPath("$[2].price", equalTo(120.0)),
                                jsonPath("$[2].picturePath", equalTo("url5"))
                        )
                );
    }

}
