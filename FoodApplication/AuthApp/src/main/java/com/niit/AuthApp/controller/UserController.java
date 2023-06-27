package com.niit.AuthApp.controller;

import com.niit.AuthApp.domain.User;
import com.niit.AuthApp.service.SecurityTokenGenerator;
import com.niit.AuthApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/user/v1")
public class UserController {

    private UserService userService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }


    //    http://localhost:8085/api/user/v1/register
    @PostMapping("/register")
    public ResponseEntity registerAccount(@RequestBody User user){

        user.setRole("User_Role");
        return new ResponseEntity<>(userService.registerAccount(user),HttpStatus.CREATED);
    }

    //    http://localhost:8085/api/user/v1/login
    @PostMapping("/login")
    public ResponseEntity loginToAccount(@RequestBody User user){
        User insertDetails = userService.checkUserLogin(user);
        if(insertDetails != null) {
            return new ResponseEntity<>(securityTokenGenerator.generateToken(insertDetails),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Login Failed...",HttpStatus.EXPECTATION_FAILED);
        }
    }
    //    http://localhost:8085/api/user/v1/get-User

    @GetMapping("/get-User/{email}")
    public ResponseEntity findByEmail(@PathVariable String email){
      return new ResponseEntity<>(userService.findByEmail(email),HttpStatus.OK);
    }

}
