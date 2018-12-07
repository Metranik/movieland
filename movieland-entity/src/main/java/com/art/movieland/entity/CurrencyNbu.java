package com.art.movieland.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyNbu {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");

    private float rate;
    @JsonProperty("cc")
    private String currencyCode;
    @JsonProperty("exchangedate")
    private String exchangeDateString;
    private LocalDate exchangeDate;

    public void setExchangeDateString(String exchangeDateString) {
        this.exchangeDateString = exchangeDateString;
        this.exchangeDate = LocalDate.parse(exchangeDateString, FORMATTER);
    }
}
