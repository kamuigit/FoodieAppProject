package com.niit.FavouriteService.repository;

import com.mongodb.client.MongoDatabase;
import com.niit.FavouriteService.domain.UserFavourite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFavRepository extends MongoRepository<UserFavourite,String> {
}
