package com.weatherapi.test.weather_api.model;

import lombok.Data;

@Data
public class Weather {
    private String headline;
    private String description;
    private String icon;
    private String city;
    private String currentTemp;
    private String minTemp;
    private String maxTemp;
    private String sunrise;
    private String sunset;
    private String unit;
}
