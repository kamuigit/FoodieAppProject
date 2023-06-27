package com.niit.FavouriteService.repository;

import com.niit.FavouriteService.domain.Restaurant;
import jdk.jfr.RecordingState;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant,String> {
}
