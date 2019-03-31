package com.weatherapi.test.weather_api.service;

import com.weatherapi.test.weather_api.config.WeatherDatabaseConfig;
import com.weatherapi.test.weather_api.dao.UserDao;
import com.weatherapi.test.weather_api.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/*
* service that manages repository operations for User object and also performs some basic validation
* to preven pages from breaking
* */
@Service
public class UserService {
    private final static Logger LOGGER = Logger.getLogger(WeatherDatabaseConfig.class.getName());

    @Autowired
    UserDao userDao;

    public void save(UserData userData){
        // check if the user already exists
        userDao.saveData(userData);
    }

    public boolean userLoginExist(UserData userData){
        List<UserData> data = userDao.readDataByName(userData);
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

    public boolean userRegisterExist(UserData userData) {
        List<UserData> registeredUsers = userDao.readDataByName(userData);
        // if size is 0 means no user present
        if(registeredUsers.size()!=0){
            if(registeredUsers.get(0).getName().toLowerCase().equals(userData.getName().toLowerCase())){
                return true;
            }
        }
        return false;
    }

    public boolean doBirthdayExist(UserData userData) throws ParseException {
        List<UserData> registeredUsers = userDao.readDataByBirthday(userData);
        // if size is 0 means no user present
        if(registeredUsers.size()!=0){
            SimpleDateFormat dateFormatter=new SimpleDateFormat("MM-dd-yyyy");
            Date userFromDbDate = dateFormatter.parse(registeredUsers.get(0).getDateOfBirth());
            Date userNewlyRegisteredDate = dateFormatter.parse(userData.getDateOfBirth());
            if(userFromDbDate.equals(userNewlyRegisteredDate)){
                return true;
            }
        }
        return false;
    }
}
