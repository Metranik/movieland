package com.art.movieland.service;

import com.art.movieland.entity.Currency;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class DefaultCurrencyServiceTest {

    @Test
    public void testGetRate() {
        CurrencyService currencyService = new DefaultCurrencyService();

        float rateEUR = currencyService.getRate(Currency.EUR);
        System.out.println(rateEUR);
        assertTrue(rateEUR > 0);

        float rateUSD = currencyService.getRate(Currency.USD);
        System.out.println(rateUSD);
        assertTrue(rateUSD > 0);
    }
}
