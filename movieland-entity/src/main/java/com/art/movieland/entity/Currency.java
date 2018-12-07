package com.art.movieland.entity;

import java.util.Optional;

public enum Currency {
    UAH("UAH"),
    EUR("EUR"),
    USD("USD");

    private String value;

    Currency(String value) {
        this.value = value;
    }

    public static Optional<Currency> of(String name) {
        for (Currency currency : Currency.values()) {
            if (currency.value.equalsIgnoreCase(name)) {
                return Optional.of(currency);
            }
        }
        return Optional.empty();
    }

    public String getValue() {
        return value;
    }
}
