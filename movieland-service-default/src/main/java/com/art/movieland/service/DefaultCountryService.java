package com.art.movieland.service;

import com.art.movieland.dao.CountryDao;
import com.art.movieland.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCountryService implements CountryService {
    private CountryDao countryDao;

    @Autowired
    public DefaultCountryService(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public List<Country> getByMovie(int movieId) {
        return countryDao.getByMovie(movieId);
    }

    @Override
    public List<Country> getAll() {
        return countryDao.getAll();
    }
}
