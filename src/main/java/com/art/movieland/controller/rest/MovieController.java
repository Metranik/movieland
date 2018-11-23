package com.art.movieland.controller.rest;

import com.art.movieland.entity.Movie;
import com.art.movieland.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(path = {"/v1/movie", "/v1/movie/json"},
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getAll() {
        return movieService.getAll();
    }

    @GetMapping(path = "/v1/movie/xml",
            produces = MediaType.APPLICATION_XML_VALUE)
    public List<Movie> getAllXml() {
        return movieService.getAll();
    }

    @GetMapping(path = {"/v1/movie/random", "/v1/movie/random/json"},
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getRandom() {
        return movieService.getRandom(3);
    }

    @GetMapping(path = "/v1/movie/random/xml",
            produces = MediaType.APPLICATION_XML_VALUE)
    public List<Movie> getRandomXml() {
        return movieService.getRandom(3);
    }

}
