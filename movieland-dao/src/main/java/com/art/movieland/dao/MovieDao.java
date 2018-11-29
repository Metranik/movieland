package com.art.movieland.dao;

import com.art.movieland.entity.Movie;
import com.art.movieland.entity.SortMovie;

import java.util.List;

public interface MovieDao {
    List<Movie> getAll(SortMovie sortMovie);
    List<Movie> getRandom(int count);
    List<Movie> getByGenre(int genreId, SortMovie sortMovie);
}