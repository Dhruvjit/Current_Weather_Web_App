package com.weatherapi.test.weather_api.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/*
* use this class only for test purpose -> testing database connection with JDBC url
* */
public class TestDatabaseConnection {

    public void testDb(){

        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/weather_map?useSSL=false&serverTimezone=UTC";
        String user = "hbstudent";
        String pass = "hbstudent";

        try{
            System.out.println("connecting to database: " + jdbcUrl);
            Connection connection = DriverManager.getConnection(jdbcUrl,user,pass);
            System.out.println("connection Successful");

        } catch (Exception e){
            System.out.println("Connection Failed");
        }
    }

}
