package com.niit.FoodieApp.repository;

import com.niit.FoodieApp.domain.ResturantCity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepositiory extends MongoRepository<ResturantCity,String> {
    public ResturantCity findByCity(String city);
}
