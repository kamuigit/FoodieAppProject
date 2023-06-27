package com.niit.FavouriteService.controller;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.niit.FavouriteService.domain.Menu;
import com.niit.FavouriteService.domain.Restaurant;
import com.niit.FavouriteService.exception.NoItemInFavourite;
import com.niit.FavouriteService.service.UserFavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favouriteService/v1/")
public class UserFavController {
    UserFavouriteService userFavouriteService;

@Autowired
    public UserFavController(UserFavouriteService userFavouriteService) {
        this.userFavouriteService = userFavouriteService;
    }

    //        http://localhost:8083/api/favouriteService/v1/addRestaurant/{email}
    @PostMapping("/addRestaurant/{email}")
    public ResponseEntity<?> addRestaurantToFav(@RequestBody Restaurant restaurant, @PathVariable("email") String email){
    return new ResponseEntity<>(userFavouriteService.addRestaurantToFavorites(email,restaurant), HttpStatus.CREATED);
    }
    @GetMapping("resturants/{email}")
    public ResponseEntity<?> getAllResturantByEmail(@PathVariable("email") String email) throws NoItemInFavourite {
    return new ResponseEntity<>(userFavouriteService.getALLFavRestaurantByEmail(email),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{email}/{resName}")
    public ResponseEntity<?> deleteRestaurantFromFavById(@PathVariable String email,@PathVariable String resName){
    return new ResponseEntity<>(userFavouriteService.deleteRestaurantFromFavById(email,resName),HttpStatus.OK);
    }

    @PostMapping("/addMenu/{email}")
    public ResponseEntity<?> addMenuToFav(@RequestBody Menu menu, @PathVariable("email") String email){
        return new ResponseEntity<>(userFavouriteService.addMenuToFavorites(email,menu), HttpStatus.CREATED);
    }
    @GetMapping("get-menu/{email}")
    public ResponseEntity<?> getAllMenuByEmail(@PathVariable("email") String email){
        return new ResponseEntity<>(userFavouriteService.getALLFavMenuByEmail(email),HttpStatus.OK);
    }

    @DeleteMapping("/delete-menu/{email}/{resName}")
    public ResponseEntity<?> deleteMenuFromFavById(@PathVariable String email,@PathVariable String resName){
        return new ResponseEntity<>(userFavouriteService.deleteMenuFromFavById(email,resName),HttpStatus.OK);
    }
}
