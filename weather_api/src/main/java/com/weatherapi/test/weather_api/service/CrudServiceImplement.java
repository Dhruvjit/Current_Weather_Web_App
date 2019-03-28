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
    public void edit(Weather weather, String city){
        crudDao.edit(weather,city);
    }

    @Transactional
    public void delete(Weather weather){
        crudDao.delete(weather);
    }

    @Transactional
    public List getAllWeatherList(){
        return crudDao.getAllWeatherList();
    }

}
