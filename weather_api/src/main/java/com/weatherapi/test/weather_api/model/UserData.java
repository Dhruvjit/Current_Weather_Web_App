package com.weatherapi.test.weather_api.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="user_details")
@Data
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "pass")
    private String Password;

    @Column(name = "date_of_birth", unique = true)
    private String dateOfBirth;
}
