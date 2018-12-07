package com.art.movieland.controller.rest;

import com.art.movieland.entity.Country;
import com.art.movieland.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1")
public class CountryController {
    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping(path = "/country",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Country> getAll() {
        return countryService.getAll();
    }
}
