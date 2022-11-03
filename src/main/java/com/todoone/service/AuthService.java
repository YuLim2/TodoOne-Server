package com.todoone.service;

import com.todoone.domain.User;
import com.todoone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void encodePW(User user) {
        String enPw = passwordEncoder.encode(user.getPassword());
        System.out.println(enPw);
        user.setPassword(enPw);
    }

    public boolean encodePWForCheck(String cuPw, User user) {
        return passwordEncoder.matches(user.getPassword(), cuPw);
    }

    public void makeCode(User user) { // 코드 생성
        int code;
        while (true) {
            code = (int) (Math.random() * 99999) + 1;
            Map<String, Object> isCode = userRepository.findByCode(code);
            if (isCode == null) break;
        }
        user.setCode(code);
    }

}
