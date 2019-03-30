package com.weatherapi.test.weather_api.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Service
public class ConvertMillisecondsService {
    public String convertToTime(long milliSeconds){
        Date dateObject = new Date(milliSeconds);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return timeFormat.format(dateObject);
    }
}
