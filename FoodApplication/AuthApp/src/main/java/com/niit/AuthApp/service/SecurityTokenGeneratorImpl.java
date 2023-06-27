package com.niit.AuthApp.service;

import com.niit.AuthApp.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class SecurityTokenGeneratorImpl implements SecurityTokenGenerator{
    @Override
    public Map<String, String> generateToken(User user) {
        Map<String,String> result = new HashMap<>();
        Map<String,Object> userDetails = new HashMap<>();
        userDetails.put("email", user.getEmail());
        userDetails.put("role",user.getRole());
        userDetails.put("password",user.getPassword());
        String myToken = Jwts.builder()
                .setClaims(userDetails)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512,"secretKey55")
                .compact();
        result.put("email", user.getEmail());
        result.put("role", user.getRole());
        result.put("token",myToken);
        result.put("message","User logged In Successfully..:)");
        return result;
    }
}
