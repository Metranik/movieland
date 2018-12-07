package com.art.movieland.service;

import com.art.movieland.entity.Currency;
import com.art.movieland.entity.CurrencyNbu;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

@Service
public class DefaultCurrencyService implements CurrencyService {
    private static final String URL_NBU_STAT_SERVICE = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode={0}&json";

    @Override
    public float getRate(Currency currency) {
        try {
            URL url = new URL(MessageFormat.format(URL_NBU_STAT_SERVICE, currency));
            try {
                CurrencyNbu[] currencyNbu =  new ObjectMapper().readValue(url, CurrencyNbu[].class);
                return currencyNbu[0].getRate();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
