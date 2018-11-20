package com.art.movieland.dao;

import com.art.movieland.entity.Movie;
import java.util.List;

@FunctionalInterface
public interface MovieDao {
    List<Movie> getAll();
}