package com.art.movieland.entity;

public enum MovieQueryField {
    RATING,
    PRICE;

    public static MovieQueryField of(String name) {
        if (name == null || name.equalsIgnoreCase(MovieQueryField.RATING.toString())){
            return MovieQueryField.RATING;
        }else if (name.equalsIgnoreCase(MovieQueryField.PRICE.toString())){
            return MovieQueryField.PRICE;
        }else {
            throw new IllegalArgumentException(name);
        }
    }
}
