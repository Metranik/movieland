package com.art.movieland.controller.rest;

import com.art.movieland.entity.Movie;
import com.art.movieland.entity.MovieParam;
import com.art.movieland.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;


@RestController
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(path = {"/v1/movie"},
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getAll(@RequestParam LinkedHashMap<String, String> requestParam) {
        return movieService.getAll(new SortMovie(requestParam));
    }

    @GetMapping(path = {"/v1/movie/random"},
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getRandom() {
        return movieService.getRandom();
    }

    @GetMapping(path = {"/v1/movie/genre/{genreId}"},
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getByGenre(@PathVariable int genreId,
                                  @RequestParam LinkedHashMap<String, String> requestParam) {
        return movieService.getByGenre(genreId, new SortMovie(requestParam));
    }

}
