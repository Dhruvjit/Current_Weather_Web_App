package com.weatherapi.test.weather_api.service;

import com.weatherapi.test.weather_api.dao.CrudDao;
import com.weatherapi.test.weather_api.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public class CrudServiceImplement implements CrudService {

    @Autowired
    private GetWeatherService getWeatherService;

    @Autowired
    private CrudDao crudDao;

    @Autowired
    private MillisecondsToTimeService millisecondsToTimeService;

    @Transactional
    public void add(Weather weather){
        crudDao.add(weather);
    }

    @Transactional
    public void edit(Weather weather, String city){
        crudDao.edit(weather,city);
    }

    @Transactional
    public void update(Weather weather) throws IOException {
        Weather latestWeather = getWeatherService.getNewWeatherObject(weather.getCity());
        String timeConvertedSunrise = millisecondsToTimeService.convertToTime(Long.parseLong(latestWeather.getSunrise()));
        String timeConvertedSunset = millisecondsToTimeService.convertToTime(Long.parseLong(latestWeather.getSunset()));
        latestWeather.setSunrise(timeConvertedSunrise);
        latestWeather.setSunset(timeConvertedSunset);
        crudDao.update(latestWeather);
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
