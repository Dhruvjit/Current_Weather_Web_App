package com.weatherapi.test.weather_api.service;

import com.weatherapi.test.weather_api.dao.SaveWeatherData;
import com.weatherapi.test.weather_api.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class SaveToDatabaseService {

    @Autowired
    private ReadJsonObjectService readJsonObjectService;

    public Weather extractWeatherObject(String result)
            throws IOException {
        Weather weather = readJsonObjectService.setWeatherModel(result);
        return weather;
    }
}
