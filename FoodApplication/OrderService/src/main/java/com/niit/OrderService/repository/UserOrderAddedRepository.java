package com.niit.OrderService.repository;

import com.niit.OrderService.domain.UserOrderAdded;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrderAddedRepository extends MongoRepository<UserOrderAdded,String> {
}
