package com.niit.FavouriteService.repository;

import com.niit.FavouriteService.domain.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends MongoRepository<Menu,String> {
}
