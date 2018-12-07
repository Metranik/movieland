package com.art.movieland.service;

import com.art.movieland.entity.Currency;
import com.art.movieland.entity.CurrencyNbu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DefaultCurrencyService implements CurrencyService {
    private static final String URL_NBU_STAT_SERVICE = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode={0}&json";
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private Map<Currency, CurrencyNbu> cacheCurrency = new ConcurrentHashMap<>();

    @Override
    public float getRate(Currency currency) {
        if (currency == null || currency == Currency.UAH) {
            return 1f;
        }

        CurrencyNbu currencyNbu = cacheCurrency.get(currency);
        if (currencyNbu == null || LocalDate.now().isAfter(currencyNbu.getExchangeDate())) {
            logger.debug("Getting {} from NBU Service ...", currency);
            currencyNbu = getCurrencyNbu(currency);
            cacheCurrency.put(currency, currencyNbu);
        }
        logger.debug("currencyNbu: {}", currencyNbu);
        logger.trace("cacheCurrency: {}", cacheCurrency);

        return currencyNbu.getRate();
    }

    public CurrencyNbu getCurrencyNbu(Currency currency) {
        try {
            URL url = new URL(MessageFormat.format(URL_NBU_STAT_SERVICE, currency));
            try {
                CurrencyNbu[] currencyNbu = new ObjectMapper().readValue(url, CurrencyNbu[].class);
                return currencyNbu[0];
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}