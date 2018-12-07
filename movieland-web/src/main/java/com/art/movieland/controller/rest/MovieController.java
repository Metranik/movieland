package com.art.movieland.controller.rest;

import com.art.movieland.controller.converter.DtoConverter;
import com.art.movieland.entity.Movie;
import com.art.movieland.entity.MovieParam;
import com.art.movieland.controller.dto.MovieDto;
import com.art.movieland.controller.dto.Views;
import com.art.movieland.service.MovieService;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
public class MovieController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(path = "/v1/movie",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getAll(@RequestParam LinkedHashMap<String, String> requestParam) {
        return movieService.getAll(new MovieParam(requestParam));
    }

    @GetMapping(path = "/v1/movie/random",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getRandom() {
        return movieService.getRandom();
    }

    @GetMapping(path = "/v1/movie/genre/{genreId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getByGenre(@PathVariable int genreId,
                                  @RequestParam LinkedHashMap<String, String> requestParam) {
        return movieService.getByGenre(genreId, new MovieParam(requestParam));
    }

    @GetMapping(path = "/v1/movie/{movieId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MovieDto getById(@PathVariable int movieId,
                            @RequestParam LinkedHashMap<String, String> requestParam) {
        Movie movie = movieService.getById(movieId, new MovieParam(requestParam));
        logger.debug("Movie getById: {}", movie);
        return DtoConverter.convertToDto(movie);
    }
}
