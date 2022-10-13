package com.todoone.service;

import com.todoone.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private String key = "xnendnjs";

    public String createJWT(User user) {
        System.out.println("Start building JWT");

        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("user_id", user.getUser_id());
        payloads.put("email", user.getEmail());
        payloads.put("pw", user.getPassword());
        payloads.put("nickname", user.getNickname());

        String accessToken = Base64.getEncoder().encodeToString(key.getBytes());

        return  Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .signWith(SignatureAlgorithm.HS256, accessToken)
                .compact();
    }

    public Map<String, Object> checkJWT(String jwt) throws UnsupportedEncodingException {
        Map<String, Object> claimMap = null;
        try {
            Claims claims = Jwts.parser().setSigningKey(key.getBytes("UTF-8")).parseClaimsJws(jwt).getBody();
            claimMap = claims;
        } catch (ExpiredJwtException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return claimMap;
    }
}