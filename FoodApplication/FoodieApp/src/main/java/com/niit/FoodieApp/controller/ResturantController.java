package com.niit.FoodieApp.controller;

import com.niit.FoodieApp.domain.Menu;
import com.niit.FoodieApp.domain.Resturant;
import com.niit.FoodieApp.domain.ResturantCity;
import com.niit.FoodieApp.exception.*;
import com.niit.FoodieApp.service.RestaurantService;
import com.niit.FoodieApp.service.ResturantServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/food/resturant")
@RestController
public class ResturantController {

    RestaurantService restaurantService;

    @Autowired
    public ResturantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


//   http://localhost:8081/food/resturant/add/city       [post]
    @PostMapping("/add/city")
    public ResponseEntity<?> saveCity(@RequestBody ResturantCity resturantCity, HttpServletRequest httpServletRequest) throws CityAlreadyExistsException {
        if(httpServletRequest.getAttribute("attr2") != null && httpServletRequest.getAttribute("attr2").equals("Admin_Role")){
            return new ResponseEntity<>(restaurantService.saveCity(resturantCity), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity("You are Not Authorized to access this link...",HttpStatus.BAD_REQUEST);
        }
    }
    //   http://localhost:8081/food/resturant/all
    @GetMapping("/all")
    public ResponseEntity<?> getAllCities() {
        return new ResponseEntity<>(restaurantService.getAllCities(), HttpStatus.OK);
    }
    //   http://localhost:8081/food/resturant/addRestaurant/{city}
    @PutMapping("/addRestaurant/{city}")
    public ResponseEntity<?> saveRestaurantInCity(HttpServletRequest httpServletRequest,@RequestBody Resturant restaurant, @PathVariable("city") String city) throws RestaurantAlreadyExistsException, CityNotFoundException, RestaurantAlreadyExistsException, CityNotFoundException {
        if(httpServletRequest.getAttribute("attr2") != null && httpServletRequest.getAttribute("attr2").equals("Admin_Role")) {
            return new ResponseEntity<>(restaurantService.saveRestaurantInCity(city, restaurant), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity("You are Not Authorized to access this link...",HttpStatus.BAD_REQUEST);
        }
    }


    //   http://localhost:8081/food/resturant/allRestaurants/{city}
    @GetMapping("/allRestaurants/{city}")
    public ResponseEntity<?> getAllRestaurantsByCity(@PathVariable("city") String city) {
        return new ResponseEntity<>(restaurantService.getAllRestaurantsByCity(city), HttpStatus.OK);
    }
    //   http://localhost:8081/food/resturant/addMenu/{city}/{restaurant}

    @PutMapping("/addMenu/{city}/{restaurant}")
    public ResponseEntity<?> saveMenuToRestaurant(@RequestBody Menu menu, @PathVariable("city") String city, @PathVariable("restaurant") String restaurant) throws CityNotFoundException, ItemAlreadyExistsException, RestaurantNotFoundException {
        return new ResponseEntity<>(restaurantService.saveMenuToRestaurant(city, restaurant, menu), HttpStatus.CREATED);
    }
    //   http://localhost:8081/food/resturant/menu/{city}/{restaurant}
    @GetMapping("/menu/{city}/{restaurant}")
    public ResponseEntity<?> getMenuByRestaurant(@PathVariable("city") String city, @PathVariable("restaurant") String restaurant) {
        return new ResponseEntity<>(restaurantService.getMenuByRestaurant(city, restaurant), HttpStatus.OK);
    }


    //            http://localhost:8081/food/resturant/update-city/{city}         [put]
    @PutMapping("/update-city/{city}")
    public ResponseEntity updateDetails(HttpServletRequest httpServletRequest,@RequestBody ResturantCity resturantCity,@PathVariable String city){
        String role = (String) httpServletRequest.getAttribute("attr2");
        if(role != null && role.equals("Admin_Role")) {
            return new ResponseEntity<>(restaurantService.updateDetails(resturantCity,city),HttpStatus.OK);
        }
        else {
            return new ResponseEntity("You are Not Authorized to access this link...",HttpStatus.BAD_REQUEST);
        }
    }

    //            http://localhost:8081/food/resturant/delete-city/{city}         [delete]
    @DeleteMapping("/delete-city/{city}")
    public ResponseEntity deleteDetails(HttpServletRequest httpServletRequest,@PathVariable String city){
        String role = (String) httpServletRequest.getAttribute("attr2");
        if(role != null && role.equals("Admin_Role")) {
            return new ResponseEntity<>(restaurantService.deleteDetails(city),HttpStatus.OK);
        }
        else {
            return new ResponseEntity("You are Not Authorized to access this link...",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete-restaurant/{city}/{resName}")
    public ResponseEntity deleteRestaurantByName(@PathVariable String city,@PathVariable String resName){
        return new ResponseEntity<>(restaurantService.deleteRestaurantByName(city,resName),HttpStatus.OK);
    }

    @DeleteMapping("/delete-menu/{city}/{resName}/{itemName}")
    public ResponseEntity deleteMenuByName(@PathVariable String city,@PathVariable String resName,@PathVariable String itemName){
        return new ResponseEntity<>(restaurantService.deleteMenuByItemName(city,resName,itemName),HttpStatus.OK);
    }
    @PutMapping("/update-restaurant/{city}/{resName}")
    public ResponseEntity updateRestaurantDetails(@RequestBody Resturant resturant,@PathVariable String city,@PathVariable String resName){
        return new ResponseEntity<>(restaurantService.updateRestaurantDetails(resturant,city,resName),HttpStatus.OK);
    }

    @PutMapping("/update-menu/{city}/{resName}/{itemName}")
    public ResponseEntity updateMenuDetails(@RequestBody Menu menu,@PathVariable String city,@PathVariable String resName,@PathVariable String itemName){
        return new ResponseEntity<>(restaurantService.updateMenuList(menu,city,resName,itemName),HttpStatus.OK);
    }
}
