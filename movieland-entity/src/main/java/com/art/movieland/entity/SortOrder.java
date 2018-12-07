package com.art.movieland.entity;

import java.util.Optional;

public enum SortOrder {
    ASC("asc"),
    DESC("desc");

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
