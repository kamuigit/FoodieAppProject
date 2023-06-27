package com.example.FavoriteService.Repository;

import com.example.FavoriteService.Domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFavoriteRepository extends MongoRepository<Customer,String> {
}
