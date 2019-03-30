package com.weatherapi.test.weather_api.dao;

import com.weatherapi.test.weather_api.config.UserDatabaseConfig;
import com.weatherapi.test.weather_api.model.UserData;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDatabase {

    ApplicationContext factory = new AnnotationConfigApplicationContext(UserDatabaseConfig.class);
    UserDatabaseConfig userDatabaseConfig = factory.getBean(UserDatabaseConfig.class);

    public void saveData(UserData userData){
        userDatabaseConfig.CreateUserInDatabase(userData);
    }

    public List<UserData> readData(UserData userData){
        return userDatabaseConfig.readUserFromDatabase(userData);
    }
}
