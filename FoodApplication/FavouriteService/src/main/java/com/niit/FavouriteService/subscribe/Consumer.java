package com.niit.FavouriteService.subscribe;


import com.niit.FavouriteService.service.UserFavouriteService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    private UserFavouriteService userFavouriteService;

    @RabbitListener(queues ="message_q2")
    public void getDtoFromQueueAndAddToDb(UserDTO userDTO) {
        if(userDTO.getRestaurant()!=null){
            userFavouriteService.addRestaurantToFavorites(userDTO.getEmail(),userDTO.getRestaurant());
        }if(userDTO.getMenu()!=null){
            userFavouriteService.addMenuToFavorites(userDTO.getEmail(),userDTO.getMenu());
        }

    }
}