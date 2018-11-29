package com.art.movieland.entity;

public enum SortOrder {
    ASC,
    DESC;

    public static SortOrder of(String name) {
        if (name == null || name.equalsIgnoreCase(SortOrder.ASC.toString())){
            return SortOrder.ASC;
        }else if (name.equalsIgnoreCase(SortOrder.DESC.toString())){
            return SortOrder.DESC;
        }else {
            throw new IllegalArgumentException(name);
        }
    }
}
