package com.art.movieland.dao.cache;

import com.art.movieland.dao.GenreDao;
import com.art.movieland.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CacheGenreDao implements GenreDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final GenreDao genreDao;
    private volatile Map<Integer, Genre> cache;
    private Map<Integer, List<Genre>> cacheByMovie = new ConcurrentHashMap<>();

    @Autowired
    public CacheGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public List<Genre> getAll() {
        List<Genre> genres = new ArrayList<>(cache.values());

        logger.debug("Count genres: {}", genres.size());
        return genres;
    }

    @Override
    public List<Genre> getByMovie(int movieId) {
        List<Genre> genresByMovie = cacheByMovie.get(movieId);
        if (genresByMovie == null) {
            genresByMovie = genreDao.getByMovie(movieId);
            cacheByMovie.put(movieId, genresByMovie);
        }

        logger.debug("Count genresByMovie: {}", genresByMovie.size());
        logger.trace("Genres: {}", genresByMovie);

        return new ArrayList(genresByMovie);
    }

    @Scheduled(fixedRateString = "#{${scheduled.fixedRate.cacheGenre.inHours}*60*60*1000}")
    @PostConstruct
    public void populateCache() {
        List<Genre> genres = genreDao.getAll();
        Map<Integer, Genre> populatedCache = new HashMap<>();
        genres.forEach((genre) -> populatedCache.put(genre.getId(), genre));
        cache = Collections.unmodifiableMap(populatedCache);

        logger.debug("Genres in cache: {}", genres.size());
        logger.trace("Genres: {}", genres);

        cacheByMovie.clear();
    }

}
