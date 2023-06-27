package com.niit.AuthApp.service;

import com.niit.AuthApp.domain.User;

public interface UserService {
    User checkUserLogin(User user);

    User registerAccount(User user);

    public User findByEmail(String email);


}
