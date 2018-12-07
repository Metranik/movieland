package com.art.movieland.service;

import com.art.movieland.dao.MovieDao;
import com.art.movieland.entity.Movie;
import com.art.movieland.entity.MovieParam;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultMovieService implements MovieService {
    private MovieDao movieDao;
    private CountryService countryService;
    private GenreService genreService;
    private ReviewService reviewService;
    private CurrencyService currencyService;

    private int movieRandomLimit;

    @Autowired
    public DefaultMovieService(MovieDao movieDao,
                               CountryService countryService,
                               GenreService genreService,
                               ReviewService reviewService,
                               CurrencyService currencyService) {
        this.movieDao = movieDao;
        this.countryService = countryService;
        this.genreService = genreService;
        this.reviewService = reviewService;
        this.currencyService = currencyService;
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
    public Movie getById(int id, MovieParam movieParam) {
        Movie movie = movieDao.getById(id);
        movie.setCountries(countryService.getByMovie(id));
        movie.setGenres(genreService.getByMovie(id));
        movie.setReviews(reviewService.getByMovie(id));
        float rate = currencyService.getRate(movieParam.getCurrency());
        movie.setPrice(Precision.round(movie.getPrice() / rate, 2));
        return movie;
    }

    public int getMovieRandomLimit() {
        return movieRandomLimit;
    }

    @Value("${app.movieRandomLimit:3}")
    public void setMovieRandomLimit(int movieRandomLimit) {
        this.movieRandomLimit = movieRandomLimit;
    }
}

