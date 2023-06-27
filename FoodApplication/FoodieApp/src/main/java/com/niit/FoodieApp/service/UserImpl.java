package com.niit.FoodieApp.service;

import com.niit.FoodieApp.domain.Resturant;
import com.niit.FoodieApp.domain.User;
import com.niit.FoodieApp.exception.UserAlreadyExists;
import com.niit.FoodieApp.proxy.UserProxy;
import com.niit.FoodieApp.rabbitmq.Producer;
import com.niit.FoodieApp.rabbitmq.UserDTO;
import com.niit.FoodieApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserImpl implements IUserService{

    private Producer producer;
    private UserRepository userRepository;
    private UserProxy userProxy;

    @Autowired
    public UserImpl(Producer producer, UserRepository userRepository, UserProxy userProxy) {
        this.producer = producer;
        this.userRepository = userRepository;
        this.userProxy = userProxy;
    }




    @Override
    public User RegisterUser(User user) throws UserAlreadyExists {
        if(userRepository.findById(user.getEmail()).isPresent()) {
            throw new UserAlreadyExists();
        }
        User savedUser = userRepository.save(user);
        if(savedUser.getEmail() != ""){
            ResponseEntity re = userProxy.customerRegister(user);

        }
        return savedUser;
    }



    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findById(email).get();
    }

    @Override
    public User UpdateUserByEmail(String email, User user) {
        User existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setPhoneNo(user.getPhoneNo());
            existingUser.setPassword(user.getPassword());
            existingUser.setConfirmPassword(user.getConfirmPassword());
            existingUser.setAge(user.getAge());
            existingUser.setImage(user.getImage());
            existingUser.setRole(user.getRole());
            existingUser.setCity(user.getCity());
            existingUser.setHouseNo(user.getHouseNo());
            existingUser.setStreet(user.getStreet());
            existingUser.setPinCode(user.getPinCode());
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

    @Override
    public boolean addToFavorite(UserDTO userDTO) {
        producer.sendDtoToQueue(userDTO);
        return true;
    }

    @Override
    public boolean addMenuToFavorite(UserDTO userDTO) {
        producer.sendDtoToQueue(userDTO);
        return true;
    }


}
