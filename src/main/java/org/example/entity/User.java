package org.example.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private String age;

    @JsonProperty("email")
    private String email;

    @JsonProperty("login")
    private String login;

    @JsonProperty("password")
    private String password;

    public User( String name,String age, String email, String login, String password) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.login = login;
        this.password = password;
    }
}
