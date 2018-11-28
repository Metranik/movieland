package com.art.movieland.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MovieParam {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Map<MovieQueryField, SortOrder> sortMap;

    public MovieParam(Map<String, String> requestParam) {
        sortMap = new HashMap<>();
        if (requestParam != null) {
            for (Map.Entry<String, String> entry : requestParam.entrySet()) {
                sortMap.put(MovieQueryField.of(entry.getKey()),
                        SortOrder.of(entry.getValue()));
            }
            logger.debug("sortMap {}", sortMap);
        }
    }

    public Map<MovieQueryField, SortOrder> getSortMap() {
        return sortMap;
    }

}
