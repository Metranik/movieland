package com.art.movieland.entity.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateConverter extends StdConverter<String, LocalDate> {
    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    ;

    @Override
    public LocalDate convert(String value) {
        return LocalDate.parse(value, DATE_FORMATTER);
    }
}
