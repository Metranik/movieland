package com.art.movieland.entity;

import java.util.HashMap;
import java.util.Map;

public class MovieParam {

    private Map<MovieQueryField, SortOrder> sortMap;

    public MovieParam(Map<String, String> requestParam) {
        sortMap = new HashMap<>();
        if (requestParam != null) {
            for (Map.Entry<String, String> entry : requestParam.entrySet()) {
                sortMap.put(MovieQueryField.of(entry.getKey()),
                        SortOrder.of(entry.getValue()));
            }
        }
    }

    public Map<MovieQueryField, SortOrder> getSortMap() {
        return sortMap;
    }

}
