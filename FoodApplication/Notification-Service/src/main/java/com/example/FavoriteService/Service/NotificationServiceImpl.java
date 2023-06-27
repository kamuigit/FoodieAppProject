package com.example.FavoriteService.Service;

import com.example.FavoriteService.Domain.Customer;
import com.example.FavoriteService.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notiRepos;

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void welcomeMail(Customer usersDTO) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("foodie.niit@gmail.com");
        message.setTo(usersDTO.getEmail());
        message.setText("Hello "+usersDTO.getEmail()+usersDTO.getMessage()+"\n Thanks For Registering....Have A Exciting Food journey ");
        message.setSubject("Welcome Foodie app");

        mailSender.send(message);
        System.out.println("Mail Send...");
    }

    @Override
    public void orderMail(Customer usersDTO) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("foodie.niit@gmail.com");
        message.setTo(usersDTO.getEmail());
        message.setText("Hello "+usersDTO.getEmail()+usersDTO.getMessage()+"\n");
        message.setSubject("Order Details");
        mailSender.send(message);
        System.out.println("Mail Send...");
    }



}