package com.weatherapi.test.weather_api.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="weather")
@Data
public class Weather {

    @Id
    @Column(name = "city",unique = true)
    private String city;

    private String headline;

    @Column(name = "descriptions")
    private String description;

    @Transient
    private String icon;

    private String currentTemp;
    private String minTemp;
    private String maxTemp;
    private String sunrise;
    private String sunset;

    @Transient
    private String unit;
}
