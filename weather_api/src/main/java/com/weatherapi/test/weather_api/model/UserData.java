package com.weatherapi.test.weather_api.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/*
* Model Class for Users
* */
@Entity
@Table(name="user_details")
@Data
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", unique = true)
    private String name;

    @NotNull
    @Column(name = "pass")
    private String Password;

    @NotNull
    @Column(name = "date_of_birth", unique = true)
    private String dateOfBirth;
}
