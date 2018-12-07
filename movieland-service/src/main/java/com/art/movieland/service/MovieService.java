package com.art.movieland.service;

import com.art.movieland.entity.Movie;
import com.art.movieland.entity.MovieParam;

import java.util.List;

public interface MovieService {
    List<Movie> getAll(MovieParam movieParam);
    List<Movie> getRandom();
    List<Movie> getByGenre(int genreId, MovieParam movieParam);
    Movie getById(int id, MovieParam movieParam);
}
