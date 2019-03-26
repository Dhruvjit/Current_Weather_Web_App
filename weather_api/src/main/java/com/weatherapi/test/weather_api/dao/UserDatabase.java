package com.weatherapi.test.weather_api.dao;

import com.weatherapi.test.weather_api.config.UserDatabaseConfig;
import com.weatherapi.test.weather_api.model.UserData;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class UserDatabase {

    ApplicationContext factory = new AnnotationConfigApplicationContext(UserDatabaseConfig.class);
    UserDatabaseConfig userDatabaseConfig = factory.getBean(UserDatabaseConfig.class);

    public void saveData(UserData userData){
        userDatabaseConfig.CreateUserInDatabase(userData);
    }

    public UserData updateData(String user){
        UserData newUserData = new UserData();
        return userDatabaseConfig.updateUserInDatabase(newUserData,user);
    }

    public UserData readData(String user){
        return userDatabaseConfig.readUserFromDatabase(user);
    }
}
