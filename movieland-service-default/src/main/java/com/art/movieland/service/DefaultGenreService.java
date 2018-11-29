package com.art.movieland.service;

import com.art.movieland.dao.GenreDao;
import com.art.movieland.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("unused")
public class DefaultGenreService implements GenreService {

    private GenreDao genreDao;

    @Autowired
    public DefaultGenreService(@Qualifier("cacheGenreDao") GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }
}
