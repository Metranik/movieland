package com.art.movieland.entity;

import java.util.Optional;

public enum MovieQueryField {
    RATING("rating"),
    PRICE("price");

    private String value;

    MovieQueryField(String value) {
        this.value = value;
    }

    public static Optional<MovieQueryField> of(String name) {
        for (MovieQueryField movieQueryField : MovieQueryField.values()) {
            if (movieQueryField.value.equalsIgnoreCase(name)) {
                return Optional.of(movieQueryField);
            }
        }
        return Optional.empty();
    }
}
