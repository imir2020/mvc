package org.example.dao;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.entity.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * В этом классе происходит работа с данными из файла/базы
 */
public class UserDao {
    private String filePath;// нужно понять, это поле точно нужно?

    public UserDao(String path) {
    }

    //Вернуть всех пользователей
    public Optional<List<User>> getAllUsersFromJson() {
        ObjectMapper mapper = new ObjectMapper();
        List<User> list = new ArrayList<>();
        File json = new File(filePath);
        if (!json.exists() || json.length() == 0) {
            return Optional.empty();
        }

        try {
            list = mapper.readValue(json, new TypeReference<>() {
            });
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.ofNullable(list);
    }

    //Записать данные одного пользователя в json
    public boolean writeUserToJson(User user) {
        List<User> listOfUser = getAllUsersFromJson().orElse(new ArrayList<>());
        listOfUser.add(user);
        return writeListToJson(listOfUser);
    }

    //Записать данные списка пользователей в json
    private boolean writeListToJson(List<User> listOfUser) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectMapper.writeValue(new File(filePath), listOfUser);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Удалить пользователя по логину
    public boolean removeUserByLogin(String login) {
        Optional<List<User>> allUsersFromJson = getAllUsersFromJson();
        if (allUsersFromJson.isPresent()) {
            List<User> users = allUsersFromJson.get();
            Optional<User> userByLogin = getUserByLogin(login);
            if (userByLogin.isPresent()) {
                User user = userByLogin.get();
                users.remove(user);
                return writeListToJson(users);
            }
        }
        return false;
    }

    //Найти юзера по логину
    public Optional<User> getUserByLogin(String login) {
        Optional<List<User>> listOfUser = getAllUsersFromJson();

        return listOfUser.flatMap(users -> users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst());
    }

    //Добавить юзера в список
    public boolean updateUserByLogin(String login, User user) {
        Optional<List<User>> allUsersFromJson = getAllUsersFromJson();
        if (allUsersFromJson.isPresent()) {
            List<User> users = allUsersFromJson.get();
            users.removeIf(us -> us.getLogin().equals(login));
            users.add(user);
            return writeListToJson(users);
        }
        return false;
    }


    //Существует ли логин?
    public boolean isLoginExists(String login) {
        return getUserByLogin(login).isPresent();
    }

    public boolean isEmailExists(String email) {
        Optional<List<User>> listOfUser = getAllUsersFromJson();
        return listOfUser.map(users -> users.stream().anyMatch(user -> user.getEmail().equals(email)))
                .orElse(false);
    }

    //Найти пароль юзера по логину
    public Optional<String> getUserPasswordByLogin(String login) {
        return getUserByLogin(login)
                .map(User::getPassword);
    }
}