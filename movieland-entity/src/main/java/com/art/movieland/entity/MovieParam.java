package com.art.movieland.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class MovieParam {

    private Map<MovieQueryField, SortOrder> sortMap = new HashMap<>();
    private Currency currency = Currency.UAH;

    public MovieParam(Map<String, String> requestParam) {
        if (requestParam == null) {
            return;
        }
        for (Map.Entry<String, String> entry : requestParam.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (Parameter.of(key).isPresent()) {
                if (Parameter.of(key).get() == Parameter.CURRENCY) {
                    this.currency = Currency.of(value).orElse(Currency.UAH);
                }
            } else if (MovieQueryField.of(key).isPresent()) {
                sortMap.put(MovieQueryField.of(key).get(),
                        SortOrder.of(value).orElse(SortOrder.ASC));
            }
        }
    }
}
