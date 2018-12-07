package com.art.movieland.dao;

import com.art.movieland.entity.Country;

import java.util.List;

public interface CountryDao {
    List<Country> getByMovie(int movieId);

    List<Country> getAll();
}
