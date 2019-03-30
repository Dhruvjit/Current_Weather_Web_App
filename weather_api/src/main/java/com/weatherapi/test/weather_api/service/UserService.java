package com.weatherapi.test.weather_api.service;

import com.weatherapi.test.weather_api.config.WeatherDatabaseConfig;
import com.weatherapi.test.weather_api.dao.UserDatabase;
import com.weatherapi.test.weather_api.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UserService {
    private final static Logger LOGGER = Logger.getLogger(WeatherDatabaseConfig.class.getName());

    @Autowired
    UserDatabase userDatabase;

    public void save(UserData userData){
        // check if the user already exists
        userDatabase.saveData(userData);
    }

    public boolean userExist(UserData userData){
        List<UserData> data = userDatabase.readData(userData);
        // if size is 0 means no user present
        if(data.size()!=0){
            if(data.get(0).getName().equals(userData.getName())){
                return true;
            }
            if(isPasswordValid(data,userData)){
                return true;
            }
        }
        return false;
    }

    public boolean isPasswordValid(List<UserData> data,UserData userData){
        if(data.get(0).getPassword().equals(userData.getPassword())){
            return true;
        }
        return false;
    }
}
