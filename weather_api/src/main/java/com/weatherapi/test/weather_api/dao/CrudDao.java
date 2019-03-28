package com.weatherapi.test.weather_api.dao;

import com.weatherapi.test.weather_api.model.Weather;

import java.util.List;

public interface CrudDao {
    public void add(Weather weather);
    public void edit(Weather weather);
    public void delete(String city);
    public Weather getWeather(String city);
    public List getAllWeatherList();
}
