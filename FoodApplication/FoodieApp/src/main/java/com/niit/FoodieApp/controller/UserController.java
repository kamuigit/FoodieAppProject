package com.niit.FoodieApp.controller;

import com.niit.FoodieApp.domain.User;
import com.niit.FoodieApp.exception.UserAlreadyExists;
import com.niit.FoodieApp.rabbitmq.UserDTO;
import com.niit.FoodieApp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.niit.FoodieApp.domain.Menu;
import com.niit.FoodieApp.domain.Resturant;

import java.io.IOException;
import java.net.URI;
import java.util.NoSuchElementException;
@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private IUserService userService;

    //      http://localhost:8081/api/v1/register-customer
    @PostMapping("/register-customer")
    public ResponseEntity<?> addCustomer(@RequestBody User user) throws UserAlreadyExists {
        user.setRole("User_Role");
        return new ResponseEntity<>(userService.RegisterUser(user),HttpStatus.CREATED);
    }


        @GetMapping("get-all")
        public ResponseEntity<?> getUser(){
            return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
        }

    @GetMapping("/user/{email}")
    public ResponseEntity getProfilePicture(@PathVariable String email) {
        return new ResponseEntity<>(userService.getUserByEmail(email),HttpStatus.OK);
    }
    //-----------------------------------------------------------------------------------------
    //      http://localhost:8081/api/v1/addRestaurant/{email}
    @PostMapping("/addRestaurant/{email}")
    public ResponseEntity<?> addRestaurantToFav(@RequestBody Resturant restaurant, @PathVariable("email") String email){

        UserDTO userDTO =new UserDTO();
        userDTO.setRestaurant(restaurant);
        userDTO.setEmail(email);

        return new ResponseEntity<>(userService.addToFavorite(userDTO), HttpStatus.CREATED);
    }
//       http://localhost:8081/api/v1/addMenu/{email}

    @PostMapping("/addMenu/{email}")
    public ResponseEntity<?> addMenuToFav(@RequestBody Menu menu, @PathVariable("email") String email){

        UserDTO userDTO =new UserDTO();
        userDTO.setMenu(menu);
        userDTO.setEmail(email);

        return new ResponseEntity<>(userService.addMenuToFavorite(userDTO), HttpStatus.CREATED);
    }
    @PutMapping("/updateUser/{email}")
    public ResponseEntity updateUser(@PathVariable String email, @RequestBody User user){
        return new ResponseEntity<>(userService.UpdateUserByEmail(email,user),HttpStatus.OK);
    }
}
