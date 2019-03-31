package com.weatherapi.test.weather_api.service;

import com.weatherapi.test.weather_api.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/*
* This service returns real time weather information from openweathermap API
* and converts it into Weather object
* */
@Service
public class GetWeatherService {

    @Autowired
    private ReadJsonObjectService readJsonObjectService;

    public Weather getNewWeatherObject(String city) throws IOException {
        String appid = "47c473417a4db382820a8d058f2db194";
        String weatherUrl = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&APPID="+appid+"&units=metric";
        RestTemplate restTemplate = new RestTemplate();
        String restTemplateQuery = restTemplate.getForObject(weatherUrl,String.class);
        Weather weather = readJsonObjectService.setWeatherModel(restTemplateQuery);
        return weather;
    }
}
