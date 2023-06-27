package com.niit.FoodieApp.rabbitmq;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    @Autowired
    private DirectExchange directExchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDtoToQueue(UserDTO userDTO){
        rabbitTemplate.convertAndSend(directExchange.getName(),"prox",userDTO);
    }

}
