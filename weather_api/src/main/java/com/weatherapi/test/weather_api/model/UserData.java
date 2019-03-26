package com.weatherapi.test.weather_api.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_details")
@Data
public class UserData {

    @Id
    @Column(name = "username",unique = true)
    private String username;

    private String email;

    @Column(name = "pass")
    private String Password;

    @Column(name = "date_of_birth", unique = true)
    private String dateOfBirth;
}
