package com.art.movieland.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SortMovie {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Map<MovieQueryField, SortOrder> sortMap;

    public SortMovie(Map<String, String> requestParam) {
        sortMap = new HashMap<>();
        if (requestParam != null) {
            for (Map.Entry<String, String> entry : requestParam.entrySet()) {
                try {
                    sortMap.put(MovieQueryField.valueOf(entry.getKey()),
                            SortOrder.valueOf(entry.getValue()));
                } catch (IllegalArgumentException e) {
                    logger.warn("Validation: {}", e);
                }
            }
            logger.debug("sortMap {}", sortMap);
        }
    }

    @Override
    public String toString() {
        if (sortMap == null || !sortMap.keySet().iterator().hasNext()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(" ORDER BY ");
        for (Iterator<Map.Entry<MovieQueryField, SortOrder>> i = sortMap.entrySet().iterator(); i.hasNext(); ) {
            Map.Entry entry = i.next();
            stringBuilder.append(entry.getKey()).append(" ").append(entry.getValue());
            if (i.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }
}
