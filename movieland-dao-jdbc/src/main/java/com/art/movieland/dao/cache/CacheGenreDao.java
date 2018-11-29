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

@Repository
public class CacheGenreDao implements GenreDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final GenreDao genreDao;
    private volatile Map<Integer, Genre> cache;

    @Autowired
    public CacheGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public List<Genre> getAll() {
        List<Genre> genres = new ArrayList<>(cache.values());

        logger.debug("Count genres for getAll: {}", genres.size());
        return genres;
    }

    @Scheduled(fixedRateString = "${cache.scheduled.fixedRate.inMilliseconds}")
    @PostConstruct
    @SuppressWarnings("unused")
    public void populateCache() {
        List<Genre> genres = genreDao.getAll();
        Map<Integer, Genre> populatedCache = new HashMap<>();
        genres.forEach((genre) -> populatedCache.put(genre.getId(), genre));
        cache = Collections.unmodifiableMap(populatedCache);

        logger.debug("Genres in cache: {}", genres);
    }

}
