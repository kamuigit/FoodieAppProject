package com.niit.FavouriteService.subscribe;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    @Bean
    public Jackson2JsonMessageConverter getMusicJackSonConv(){
        return  new Jackson2JsonMessageConverter();
    }
}
