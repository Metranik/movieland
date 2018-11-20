package com.art.movieland.controller.rest;

import com.art.movieland.entity.Movie;
import com.art.movieland.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(path = "/v1/movie")
    public List<Movie> getAll() {
        return movieService.getAll();
    }

}