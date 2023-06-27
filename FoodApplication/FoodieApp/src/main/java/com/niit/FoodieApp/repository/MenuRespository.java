package com.niit.FoodieApp.repository;

import com.niit.FoodieApp.domain.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MenuRespository extends MongoRepository<Menu,String> {


}
