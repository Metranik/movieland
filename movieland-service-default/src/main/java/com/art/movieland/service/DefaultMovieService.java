package com.art.movieland.service;

import com.art.movieland.dao.MovieDao;
import com.art.movieland.entity.Movie;
import com.art.movieland.entity.MovieFull;
import com.art.movieland.entity.MovieParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("unused")
public class DefaultMovieService implements MovieService {
    private MovieDao movieDao;
    private CountryService countryService;
    private GenreService genreService;
    private ReviewService reviewService;

    private int movieRandomLimit;

    @Autowired
    public DefaultMovieService(MovieDao movieDao,
                               CountryService countryService,
                               GenreService genreService,
                               ReviewService reviewService) {
        this.movieDao = movieDao;
        this.countryService = countryService;
        this.genreService = genreService;
        this.reviewService = reviewService;
    }

    @Override
    public List<Movie> getAll(MovieParam movieParam) {
         return movieDao.getAll(movieParam);
    }

    @Override
    public List<Movie> getRandom() {
        return movieDao.getRandom(movieRandomLimit);
    }

    @Override
    public List<Movie> getByGenre(int genreId, MovieParam movieParam) {
        return movieDao.getByGenre(genreId, movieParam);
    }

    @Override
    public MovieFull getById(int id) {
        Movie movie = movieDao.getById(id);
        MovieFull movieFull = new MovieFull(movie);
        movieFull.setCountries(countryService.getByMovie(id));
        movieFull.setGenres(genreService.getByMovie(id));
        movieFull.setReviews(reviewService.getByMovie(id));
        return movieFull;
    }

    public int getMovieRandomLimit() {
        return movieRandomLimit;
    }

    @Value("${app.movieRandomLimit:3}")
    public void setMovieRandomLimit(int movieRandomLimit) {
        this.movieRandomLimit = movieRandomLimit;
    }
}

