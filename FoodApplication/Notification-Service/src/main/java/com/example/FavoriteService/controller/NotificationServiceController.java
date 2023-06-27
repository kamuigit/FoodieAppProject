package com.example.FavoriteService.controller;

import com.example.FavoriteService.Domain.Customer;
import com.example.FavoriteService.Service.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("mail")
public class NotificationServiceController {
private NotificationService notificationService;

    @Autowired
    public NotificationServiceController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/sendmail")
    public ResponseEntity<?> addRestaurantToFavorites(@RequestBody Customer userDTO  ) {

        notificationService.welcomeMail(userDTO);
        return new ResponseEntity<>( "done", HttpStatus.CREATED);
    }
    @PostMapping("/sendordermail")
    public ResponseEntity<?> sendOrdermail(@RequestBody Customer userDTO  ) {

        notificationService.orderMail(userDTO);
        return new ResponseEntity<>( "done", HttpStatus.CREATED);
    }



}
