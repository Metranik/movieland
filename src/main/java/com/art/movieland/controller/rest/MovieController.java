package com.art.movieland.controller.rest;

import com.art.movieland.entity.Movie;
import com.art.movieland.service.MovieService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    private MovieService movieService;

    @Value("${app.movieRandomCount:3}")
    private int movieRandomCount;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(path = {"/v1/movie"},
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getAll() {
        return movieService.getAll();
    }

    @GetMapping(path = {"/v1/movie/random"},
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getRandom() {
        return movieService.getRandom(movieRandomCount);
    }

    @GetMapping(path = {"/v1/movie/genre/{genreId}"},
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getByGenre(@PathVariable int genreId) {
        return movieService.getByGenre(genreId);
    }

}
