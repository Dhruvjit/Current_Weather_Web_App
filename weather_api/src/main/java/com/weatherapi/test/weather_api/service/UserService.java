package com.weatherapi.test.weather_api.service;

import com.weatherapi.test.weather_api.config.WeatherDatabaseConfig;
import com.weatherapi.test.weather_api.dao.UserDatabase;
import com.weatherapi.test.weather_api.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserService {
    private final static Logger LOGGER = Logger.getLogger(WeatherDatabaseConfig.class.getName());

    @Autowired
    UserDatabase userDatabase;

    public void validateUser(UserData userData){
        // check if the user already exists
        Object data = userDatabase.readData(userData.getUsername());
        if(data==null){
            userDatabase.saveData(userData);
        }else {
            LOGGER.info("user already exist. no changes made!");
        }
    }

    public boolean userExist(String username){
        Object data = userDatabase.readData(username);
        if(data==null){
            return false;
        }
        return true;
    }

    public boolean checkPassword(UserData userData){
        Object data = userDatabase.readData(userData.getUsername());
        String dbPass = ((UserData) data).getPassword();
        if(userData.getPassword().equals(dbPass)){
            return true;
        }
        return false;
    }
}
