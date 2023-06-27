package com.niit.AuthApp.service;

import com.niit.AuthApp.domain.User;
import com.niit.AuthApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUserLogin(User user) {
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    @Override
    public User registerAccount(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
