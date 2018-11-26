package com.art.movieland.service;

import com.art.movieland.entity.Movie;
import java.util.List;

public interface MovieService {
    List<Movie> getAll();
    List<Movie> getRandom();
    List<Movie> getByGenre(int genreId);
}
