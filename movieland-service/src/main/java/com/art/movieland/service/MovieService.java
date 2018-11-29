package com.art.movieland.service;

import com.art.movieland.entity.Movie;
import com.art.movieland.entity.SortMovie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll(SortMovie sortMovie);
    List<Movie> getRandom();
    List<Movie> getByGenre(int genreId, SortMovie sortMovie);
}
