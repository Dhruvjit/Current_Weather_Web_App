package com.weatherapi.test.weather_api.dao;

import com.weatherapi.test.weather_api.model.Weather;

import java.util.List;

public interface CrudDao {
    public void add(Weather weather);
    public void edit(Weather weather, String city);
    public void delete(Weather weather);
    public List getAllWeatherList();
}
