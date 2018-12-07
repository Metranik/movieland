package com.art.movieland.entity;

import java.util.Optional;

public enum Parameter {
    CURRENCY("currency");

    private String value;

    Parameter(String value) {
        this.value = value;
    }

    public static Optional<Parameter> of(String name) {
        for (Parameter parameter : Parameter.values()) {
            if (parameter.value.equalsIgnoreCase(name)) {
                return Optional.of(parameter);
            }
        }
        return Optional.empty();
    }
}
