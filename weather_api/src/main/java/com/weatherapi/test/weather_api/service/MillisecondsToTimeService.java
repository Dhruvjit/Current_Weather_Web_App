package com.weatherapi.test.weather_api.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/*
* this service assists in converting the time in json format represented by milliseconds
* to java format time in UTC format
* */
@Service
public class MillisecondsToTimeService {
    public String convertToTime(long milliSeconds){
        Date dateObject = new Date(milliSeconds);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return timeFormat.format(dateObject);
    }
}
