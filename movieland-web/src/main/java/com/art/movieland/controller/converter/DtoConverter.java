package com.art.movieland.controller.converter;

import com.art.movieland.entity.Movie;
import com.art.movieland.entity.dto.MovieDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DtoConverter {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public MovieDto convertToDto(Movie movie) {
        MovieDto movieDto = MODEL_MAPPER.map(movie, MovieDto.class);
        //logger.debug("MovieDto convertToDto: {}", movieDto);
        return movieDto;
    }

}
