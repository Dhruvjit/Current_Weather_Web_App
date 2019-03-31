package com.weatherapi.test.weather_api.model;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="weather", uniqueConstraints = @UniqueConstraint(columnNames = {"city"}))
@Data
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "city")
    private String city;

    private String headline;

    @Column(name = "descriptions")
    private String description;

    @Transient
    private String wind;

    @Transient
    private String icon;

    @Range(min=0, max=100, message = "length for current temperature should be less than 100")
    private float currentTemp;

    private String minTemp;

    private String maxTemp;

    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Incorrect! only HH:MM allowed where H->[0-23] and M->[0-59]")
    private String sunrise;

    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Incorrect!only HH:MM allowed where H->[0-23] and M->[0-59]")
    private String sunset;

    @Transient
    private String unit;
}
