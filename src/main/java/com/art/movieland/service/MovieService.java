package com.art.movieland.service;

import com.art.movieland.entity.Movie;
import java.util.List;

@FunctionalInterface
public interface MovieService {
    List<Movie> getAll();
}
