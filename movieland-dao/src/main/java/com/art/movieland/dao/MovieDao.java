package com.art.movieland.dao;

import com.art.movieland.entity.Movie;
import com.art.movieland.entity.MovieParam;

import java.util.List;

public interface MovieDao {
    List<Movie> getAll(MovieParam movieParam);
    List<Movie> getRandom(int count);
    List<Movie> getByGenre(int genreId, MovieParam movieParam);
    Movie getById(int id);
}