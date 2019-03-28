package com.weatherapi.test.weather_api.service;

import com.weatherapi.test.weather_api.dao.CrudDao;
import com.weatherapi.test.weather_api.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CrudServiceImplement implements CrudService {

    @Autowired
    private CrudDao crudDao;

    @Transactional
    public void add(Weather weather){
        crudDao.add(weather);
    }

    @Transactional
    public void edit(Weather weather){
        crudDao.edit(weather);
    }

    @Transactional
    public void delete(String city){
        crudDao.delete(city);
    }

    @Transactional
    public Weather getWeather(String city){
        return crudDao.getWeather(city);
    }

    @Transactional
    public List getAllWeatherList(){
        return crudDao.getAllWeatherList();
    }

}
