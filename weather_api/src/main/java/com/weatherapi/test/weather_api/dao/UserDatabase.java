package com.weatherapi.test.weather_api.dao;

import com.weatherapi.test.weather_api.config.UserDatabaseConfig;
import com.weatherapi.test.weather_api.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class UserDatabase {

    ApplicationContext factory = new AnnotationConfigApplicationContext(UserDatabaseConfig.class);
    UserDatabaseConfig userDatabaseConfig = factory.getBean(UserDatabaseConfig.class);

    public void saveData(User user){
        userDatabaseConfig.CreateUserInDatabase(user);
    }

    public void updateData(String user){
        User newUser = new User();
        userDatabaseConfig.updateUserInDatabase(newUser,user);
    }

    public void readData(String user){
        userDatabaseConfig.readUserFromDatabase(user);
    }
}
