package com.art.movieland.controller.converter;

import com.art.movieland.entity.Movie;
import com.art.movieland.controller.dto.MovieDto;
import org.modelmapper.ModelMapper;

public class DtoConverter {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public static MovieDto convertToDto(Movie movie) {
        return MODEL_MAPPER.map(movie, MovieDto.class);
    }

}
