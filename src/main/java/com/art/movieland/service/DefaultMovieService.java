package com.art.movieland.service;

import com.art.movieland.dao.MovieDao;
import com.art.movieland.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DefaultMovieService implements MovieService {

    private MovieDao movieDao;

    @Value("${app.movieRandomLimit:3}")
    private int movieRandomLimit;

    @Autowired
    public DefaultMovieService(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public List<Movie> getAll() {
         return movieDao.getAll();
    }

    @Override
    public List<Movie> getRandom() {
        return movieDao.getRandom(movieRandomLimit);
    }

    @Override
    public List<Movie> getByGenre(int genreId) {
        return movieDao.getByGenre(genreId);
    }

    public int getMovieRandomLimit() {
        return movieRandomLimit;
    }

    public void setMovieRandomLimit(int movieRandomLimit) {
        this.movieRandomLimit = movieRandomLimit;
    }
}

