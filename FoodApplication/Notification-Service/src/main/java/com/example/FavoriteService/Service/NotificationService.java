package com.example.FavoriteService.Service;

import com.example.FavoriteService.Domain.Customer;

public interface NotificationService {

    public void welcomeMail(Customer usersDTO);

    public void orderMail(Customer usersDTO);





}


