package org.example.service;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService implements Serializable {
    public Boolean isValidName(String name) {
        String regex = "^[A-Z][a-zA-Z]{2,14}$";
        Pattern pattern = Pattern.compile(regex);
        if (name == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public boolean isValidAge(String age) {
        String regex = "^(1[89]|[2-9][0-9]|[1-9][0-9]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        if (age == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(age);
        return matcher.matches();
    }

    public boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        if (email == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public Boolean isValidLogin(String login) {
        String regex = "^[A-Za-z]\\w{5,29}$";
        Pattern pattern = Pattern.compile(regex);
        if (login == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }

    public Boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-z])(?=\\S+$).{5,15}$";
        Pattern pattern = Pattern.compile(regex);
        if (password == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}

