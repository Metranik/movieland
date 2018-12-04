package com.art.movieland.service;

import com.art.movieland.entity.Country;

import java.util.List;

public interface CountryService {
    List<Country> getByMovie(int movieId);
}
