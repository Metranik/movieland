package com.art.movieland.entity;

import com.art.movieland.entity.converter.LocalDateToStringConverter;
import com.art.movieland.entity.converter.StringToLocalDateConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyNbu {
    private double rate;
    @JsonProperty("cc")
    private String currencyCode;
    @JsonProperty("exchangedate")
    @JsonSerialize(converter = LocalDateToStringConverter.class)
    @JsonDeserialize(converter = StringToLocalDateConverter.class)
    private LocalDate exchangeDate;
}