package com.art.movieland.entity.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDate;

public class LocalDateToStringConverter extends StdConverter<LocalDate, String> {

    @Override
    public String convert(LocalDate value) {
        return value.format(StringToLocalDateConverter.DATE_FORMATTER);
    }
}
