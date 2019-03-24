package com.weatherapi.test.weather_api.model;

import lombok.Data;

@Data
public class Weather {
    private String id;
    private String main;
    private String description;
    private String icon;
}
