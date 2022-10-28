package com.todoone.controller;

import com.todoone.domain.Couple;
import com.todoone.domain.User;
import com.todoone.repository.CoupleRepository;
import com.todoone.repository.UserRepository;
import com.todoone.service.AuthService;
import com.todoone.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AuthController {
    @Autowired
    private final UserRepository userRepository;
    private final CoupleRepository coupleRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthService authService;
    public AuthController(UserRepository userRepository, CoupleRepository coupleRepository) {
        this.userRepository = userRepository;
        this.coupleRepository = coupleRepository;
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

    @RequestMapping("/connect") // 코드 연동
    public String Connect(@RequestParam int user_id1, @RequestParam int code) throws Exception {
        Map<String, Object> is_exist = userRepository.findByCode(code);
        if(is_exist == null) return "실패 : 코드 존재하지 않음";
        int user_id2 = coupleRepository.findUserID(code);
        Map<String, Object> isCouple = coupleRepository.codeCheck(user_id2);
        if(isCouple != null) return "실패 : 이미 커플";
        else {
            coupleRepository.addCouple(user_id1, user_id2);
            return "성공 : 커플 연결";
        }
    }
}
