package com.art.movieland.service;

import com.art.movieland.dao.MovieDao;
import com.art.movieland.entity.Movie;
import com.art.movieland.entity.SortMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@SuppressWarnings("unused")
public class DefaultMovieService implements MovieService {

    private MovieDao movieDao;

    @Value("${app.movieRandomLimit:3}")
    private int movieRandomLimit;

    @Autowired
    public DefaultMovieService(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public List<Movie> getAll(SortMovie sortMovie) {
         return movieDao.getAll(sortMovie);
    }

    @Override
    public List<Movie> getRandom() {
        return movieDao.getRandom(movieRandomLimit);
    }

    @Override
    public List<Movie> getByGenre(int genreId, SortMovie sortMovie) {
        return movieDao.getByGenre(genreId, sortMovie);
    }

    public int getMovieRandomLimit() {
        return movieRandomLimit;
    }

    public void setMovieRandomLimit(int movieRandomLimit) {
        this.movieRandomLimit = movieRandomLimit;
    }
}

