package com.todoone.service;

import com.todoone.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void encodePW(User user) {
        String enPw = passwordEncoder.encode(user.getPassword());
        System.out.println(enPw);
        user.setPassword(enPw);
    }

    public boolean encodePWForCheck(String cuPw, User user) {
        return passwordEncoder.matches(user.getPassword(), cuPw);
    }

}
