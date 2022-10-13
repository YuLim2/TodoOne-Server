package com.todoone.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private int user_id;
    private String name;
    private String email;
    private String password;
    private String nickname;
    private String birth;
    private int gender;
    private int allowNotification;
    private int code;

    public User(int user_id, String name, String email, String password, String birth, int gender, int allowNotification, String nickname, int code) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.birth = birth;
        this.allowNotification = allowNotification;
        this.gender = gender;
        this.code = code;
    }
}