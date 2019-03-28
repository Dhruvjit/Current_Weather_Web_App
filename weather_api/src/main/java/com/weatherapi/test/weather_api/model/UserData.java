package com.weatherapi.test.weather_api.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="user_details",
        uniqueConstraints = @UniqueConstraint(columnNames = {"username", "date_of_birth"}))
@Data
public class UserData {

    @Id
    @Column(name = "username")
    private String username;

    private String email;

    @Column(name = "pass")
    private String Password;

    @Column(name = "date_of_birth")
    private String dateOfBirth;
}
