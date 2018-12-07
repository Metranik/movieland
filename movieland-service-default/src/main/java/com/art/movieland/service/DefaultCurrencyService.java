package com.art.movieland.service;

import com.art.movieland.entity.Currency;
import com.art.movieland.entity.CurrencyNbu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DefaultCurrencyService implements CurrencyService {
    private static final ParameterizedTypeReference<List<CurrencyNbu>> PARAMETERIZED_TYPE_REFERENCE = new ParameterizedTypeReference<List<CurrencyNbu>>() {
    };
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String urlNbuStatService;
    private volatile Map<String, Double> cacheCurrency = new HashMap<>();
    private RestTemplate restTemplate;

    @Autowired
    public DefaultCurrencyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public double getRate(Currency currency) {
        if (currency == null || currency == Currency.UAH) {
            return 1;
        }
        return cacheCurrency.get(currency.getValue());
    }

    @Scheduled(cron = "${nbu.statService.cron}")
    @PostConstruct
    void populateCache() {
        ResponseEntity<List<CurrencyNbu>> responseEntity = restTemplate.exchange(urlNbuStatService, HttpMethod.GET, null, PARAMETERIZED_TYPE_REFERENCE);
        List<CurrencyNbu> currencyList = responseEntity.getBody();
        Map<String, Double> currencyMap = new HashMap<>();
        currencyList.forEach(currencyNbu -> currencyMap.put(currencyNbu.getCurrencyCode(), currencyNbu.getRate()));

        cacheCurrency = Collections.unmodifiableMap(currencyMap);
        logger.trace("cacheCurrency: {}", cacheCurrency);
    }

    public String getUrlNbuStatService() {
        return urlNbuStatService;
    }

    @Value("${nbu.statService.url}")
    public void setUrlNbuStatService(String urlNbuStatService) {
        this.urlNbuStatService = urlNbuStatService;
    }
}