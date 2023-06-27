package com.niit.AuthApp.service;

import com.niit.AuthApp.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    public Map<String,String> generateToken(User user);
}
