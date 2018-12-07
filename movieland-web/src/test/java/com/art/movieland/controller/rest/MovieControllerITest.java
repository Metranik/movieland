package com.art.movieland.controller.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml",
        "classpath:testContext.xml"})
@WebAppConfiguration
public class MovieControllerITest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/v1/movie"))
                .andDo(print())
                .andExpect(
                        matchAll(
                                status().isOk(),
                                content().contentType(MediaType.APPLICATION_JSON_UTF8),
                                jsonPath("$", hasSize(25))
                        )
                );
    }

    @Test
    public void testGetAllSortedByPriceAsc() throws Exception {
        mockMvc.perform(get("/v1/movie?price=asc"))
                .andDo(print())
                .andExpect(
                        matchAll(
                                status().isOk(),
                                content().contentType(MediaType.APPLICATION_JSON_UTF8),
                                jsonPath("$", hasSize(25)),
                                jsonPath("$[0].id", equalTo(23)),
                                jsonPath("$[0].nameRussian", equalTo("Блеф")),
                                jsonPath("$[0].nameNative", equalTo("Bluff storia di truffe e di imbroglioni")),
                                jsonPath("$[0].yearOfRelease", equalTo(1976)),
                                jsonPath("$[0].rating", equalTo(7.6)),
                                jsonPath("$[0].price", equalTo(100.0)),
                                jsonPath("$[0].picturePath", notNullValue())
                        )
                );
    }

    @Test
    public void testGetAllSortedByRatingDesc() throws Exception {
        mockMvc.perform(get("/v1/movie?rating=desc"))
                .andExpect(
                        matchAll(
                                status().isOk(),
                                content().contentType(MediaType.APPLICATION_JSON_UTF8),
                                jsonPath("$", hasSize(25)),
                                jsonPath("$[0].id", equalTo(1)),
                                jsonPath("$[0].nameRussian", equalTo("Побег из Шоушенка")),
                                jsonPath("$[0].nameNative", equalTo("The Shawshank Redemption")),
                                jsonPath("$[0].yearOfRelease", equalTo(1994)),
                                jsonPath("$[0].rating", equalTo(8.9)),
                                jsonPath("$[0].price", equalTo(123.45)),
                                jsonPath("$[0].picturePath", notNullValue())
                        )
                );
    }

    @Test
    public void testGetRandom() throws Exception {
        mockMvc.perform(get("/v1/movie/random"))
                .andDo(print())
                .andExpect(
                        matchAll(
                                status().isOk(),
                                content().contentType(MediaType.APPLICATION_JSON_UTF8),
                                jsonPath("$", hasSize(3))
                        )
                );
    }

    @Test
    public void testGetByGenre() throws Exception {
        mockMvc.perform(get("/v1/movie/genre/1"))
                .andDo(print())
                .andExpect(
                        matchAll(
                                status().isOk(),
                                content().contentType(MediaType.APPLICATION_JSON_UTF8),
                                jsonPath("$", hasSize(16))
                        )
                );
    }

    @Test
    public void testGetByGenreSortedByRatingDesc() throws Exception {
        mockMvc.perform(get("/v1/movie/genre/1?rating=desc"))
                .andDo(print())
                .andExpect(
                        matchAll(
                                status().isOk(),
                                content().contentType(MediaType.APPLICATION_JSON_UTF8),
                                jsonPath("$", hasSize(16)),
                                jsonPath("$[0].id", equalTo(1)),
                                jsonPath("$[0].nameRussian", equalTo("Побег из Шоушенка")),
                                jsonPath("$[0].nameNative", equalTo("The Shawshank Redemption")),
                                jsonPath("$[0].yearOfRelease", equalTo(1994)),
                                jsonPath("$[0].rating", equalTo(8.9)),
                                jsonPath("$[0].price", equalTo(123.45)),
                                jsonPath("$[0].picturePath", notNullValue())
                        )
                );
    }

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(get("/v1/movie/1"))
                .andDo(print())
                .andExpect(
                        matchAll(
                                content().contentType(MediaType.APPLICATION_JSON_UTF8),
                                jsonPath("$.id", equalTo(1)),
                                jsonPath("$.nameRussian", equalTo("Побег из Шоушенка")),
                                jsonPath("$.nameNative", equalTo("The Shawshank Redemption")),
                                jsonPath("$.yearOfRelease", equalTo(1994)),
                                jsonPath("$.rating", equalTo(8.9)),
                                jsonPath("$.price", equalTo(123.45)),
                                jsonPath("$.picturePath", equalTo("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg")),
                                jsonPath("$.countries", hasSize(1)),
                                jsonPath("$.countries[0].name", equalTo("США")),
                                jsonPath("$.genres", hasSize(2)),
                                jsonPath("$.reviews", hasSize(2)),
                                jsonPath("$.reviews[0].user.name", equalTo("Дарлин Эдвардс")),
                                jsonPath("$.reviews[0].comment", notNullValue()),
                                jsonPath("$.reviews[1].user.name", equalTo("Габриэль Джексон")),
                                jsonPath("$.reviews[1].comment", notNullValue())
                        )
                );
    }
}
