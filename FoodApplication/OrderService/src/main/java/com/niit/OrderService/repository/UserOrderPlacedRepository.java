package com.niit.OrderService.repository;

import com.niit.OrderService.domain.UserOrderAdded;
import com.niit.OrderService.domain.UserOrderPlaced;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrderPlacedRepository extends MongoRepository<UserOrderPlaced,String> {
}
