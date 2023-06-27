package com.niit.FoodieApp.service;

import com.niit.FoodieApp.domain.Resturant;
import com.niit.FoodieApp.domain.User;
import com.niit.FoodieApp.exception.UserAlreadyExists;
import com.niit.FoodieApp.rabbitmq.UserDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IUserService {

    public User RegisterUser(User user)throws UserAlreadyExists;
    public List<User> getAllUser();
    public User getUserByEmail(String email);
    public User UpdateUserByEmail(String email,User user);
    public boolean addToFavorite(UserDTO userDTO);

    public boolean addMenuToFavorite(UserDTO userDTO);
}
