package com.art.movieland.entity;

import java.util.Optional;

public enum SortOrder {
    ASC("ASC"),
    DESC("ASC");

    private String value;

    SortOrder(String value) {
        this.value = value;
    }

    public static Optional<SortOrder> of(String name) {
        for (SortOrder sortOrder : SortOrder.values()) {
            if (sortOrder.value.equalsIgnoreCase(name)) {
                return Optional.of(sortOrder);
            }
        }
        return Optional.empty();
    }
}
