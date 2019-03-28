package com.weatherapi.test.weather_api.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

/*
* Queries needed to get weather information based on City
* */
@Data
public class WeatherQuery {
    String city;
    String appid;
    private JsonNode result;
}
