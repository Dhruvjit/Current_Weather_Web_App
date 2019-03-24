package com.weatherapi.test.weather_api.model;

import lombok.Data;

/*
* Queries needed to get weather information based on City
* */
@Data
public class WeatherQueryParams {
    String city;
    String appid;
}
