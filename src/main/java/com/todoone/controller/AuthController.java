package com.todoone.controller;

import com.todoone.domain.User;
import com.todoone.repository.UserRepository;
import com.todoone.service.AuthService;
import com.todoone.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@RestController
public class AuthController {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthService authService;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/join") // 회원가입
    public String Join(@RequestBody User user) throws Exception {
        Map<String, Object> isUser = userRepository.findById(user.getEmail());
        if(isUser != null) return "아이디 중복";
        else {
            authService.encodePW(user);
            authService.makeCode(user);
            userRepository.addUser(user);
            return jwtService.createJWT(user);
        }
    }

    @RequestMapping("/login") // 로그인
    public String Login(@RequestBody User user) throws Exception {
        Map<String, Object> isUser = userRepository.findById(user.getEmail());
        String pw = userRepository.pwCheck(user.getEmail());
        if(isUser == null) return "회원가입 필요";
        else if(!authService.encodePWForCheck(pw, user)) return "비밀번호 틀림";
        else return jwtService.createJWT(user);
    }

    @RequestMapping("/connect") // 로그인
    public String Connect(@RequestBody User user) throws Exception {
        Map<String, Object> isUser = userRepository.findById(user.getEmail());
        String pw = userRepository.pwCheck(user.getEmail());
        if(isUser == null) return "회원가입 필요";
        else if(!authService.encodePWForCheck(pw, user)) return "비밀번호 틀림";
        else return jwtService.createJWT(user);
    }
}
