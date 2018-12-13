package com.art.movieland.controller.rest;

import com.art.movieland.configuration.RootConfig;
import com.art.movieland.configuration.TestConfig;
import com.art.movieland.configuration.WebConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, WebConfig.class, TestConfig.class})
@WebAppConfiguration
public class SecurityControllerITest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testLogin() throws Exception {
        // When
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/login")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .characterEncoding("UTF-8")
                        .content("{\"email\" : \"ronald.reynolds66@example.com\", " +
                                "\"password\" : \"paco\"}");
        // Then
        mockMvc.perform(builder)
                .andDo(print())
                .andExpect(matchAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON_UTF8),
                        jsonPath("$.uuid", notNullValue()),
                        jsonPath("$.name", equalTo("Рональд Рейнольдс")))
                );
    }

    @Test
    public void testLoginAuthenticationException() throws Exception {
        // When
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/login")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .characterEncoding("UTF-8")
                        .content("{\"email\" : \"AliBaba@alibaba.com\", " +
                                "\"password\" : \"Open Sesame\"}");
        // Then
        mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("AuthenticationException"));
    }

    @Test
    public void testLoginLogout() throws Exception {
        // Login
        MockHttpServletRequestBuilder builderLogin =
                MockMvcRequestBuilders.put("/login")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .characterEncoding("UTF-8")
                        .content("{\"email\" : \"ronald.reynolds66@example.com\", " +
                                "\"password\" : \"paco\"}");
        String contentAsString =
                mockMvc.perform(builderLogin)
                        .andDo(print())
                        .andExpect(matchAll(
                                status().isOk(),
                                content().contentType(MediaType.APPLICATION_JSON_UTF8),
                                jsonPath("$.uuid", notNullValue()),
                                jsonPath("$.name", equalTo("Рональд Рейнольдс")))
                        )
                        .andReturn().getResponse().getContentAsString();

        JsonNode rootNode = (new ObjectMapper()).readTree(contentAsString);
        String uuid = rootNode.path("uuid").asText();

        // Logout
        MockHttpServletRequestBuilder builderLogout = MockMvcRequestBuilders.delete("/logout")
                .header("uuid", uuid);

        mockMvc.perform(builderLogout)
                .andDo(print())
                .andExpect(matchAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON_UTF8),
                        jsonPath("$.uuid", equalTo(uuid)),
                        jsonPath("$.name", equalTo("Рональд Рейнольдс")))
                );
    }

    @Test
    public void testLogoutNotExistingUuid() throws Exception {
        // Logout
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/logout")
                .header("uuid", "uuid");

        mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}
