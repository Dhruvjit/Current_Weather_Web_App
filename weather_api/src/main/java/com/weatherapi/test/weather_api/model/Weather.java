package com.weatherapi.test.weather_api.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="weather")
@Data
public class Weather {

    @Id
    @Column(name = "city")
    private String city;

    private String headline;

    @Column(name = "descriptions")
    private String description;

    private String icon;
    private String currentTemp;
    private String minTemp;
    private String maxTemp;
    private String sunrise;
    private String sunset;
    private String unit;
}
