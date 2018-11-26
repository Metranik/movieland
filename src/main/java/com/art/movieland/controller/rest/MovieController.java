package com.art.movieland.controller.rest;

import com.art.movieland.entity.Movie;
import com.art.movieland.entity.SortMovie;
import com.art.movieland.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(path = {"/v1/movie"},
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getAll(@RequestParam Map requestParam) {
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
                                  @RequestParam Map requestParam) {
        return movieService.getByGenre(genreId, new SortMovie(requestParam));
    }

}
