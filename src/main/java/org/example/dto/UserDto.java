package org.example.dto;

import org.example.dao.UserDao;
import org.example.entity.User;

import java.util.Optional;

public class UserDto {
    UserDao userDao = new UserDao("src/main/resources/base.json");
    public  boolean addUser(User user) {
        return userDao.writeUserToJson(user);
    }

    public Optional<User> getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    public Optional<String> getPasswordByLogin(String login) {
        return userDao.getUserPasswordByLogin(login);
    }

    public boolean isLoginExists(String login) {
        return userDao.isLoginExists(login);
    }

    public boolean isEmailExists(String email) {
        return userDao.isEmailExists(email);
    }

    public boolean updateUserByLogin(String login, User user) {
        return userDao.updateUserByLogin(login, user);
    }
}
