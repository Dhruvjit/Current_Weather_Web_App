package com.weatherapi.test.weather_api.service;

import com.weatherapi.test.weather_api.model.Weather;

import java.io.IOException;
import java.util.List;

public interface CrudService {
    public void add(Weather weather);
    public void edit(Weather weather, String city);
    public void update(Weather weather) throws IOException;
    public void delete(Weather weather);
    public List getAllWeatherList();
}
