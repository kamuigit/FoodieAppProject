package com.niit.FoodieApp.service;

import com.niit.FoodieApp.domain.Menu;
import com.niit.FoodieApp.domain.Resturant;
import com.niit.FoodieApp.domain.ResturantCity;
import com.niit.FoodieApp.exception.*;

import java.util.List;

public interface  RestaurantService {
    public ResturantCity saveCity(ResturantCity restaurantCity) throws CityAlreadyExistsException;
    public List<ResturantCity> getAllCities();
    public ResturantCity saveRestaurantInCity(String city, Resturant restaurant) throws RestaurantAlreadyExistsException, CityNotFoundException;
    public List<Resturant> getAllRestaurantsByCity(String city);
    public ResturantCity saveMenuToRestaurant(String city, String resName, Menu menu) throws CityNotFoundException, ItemAlreadyExistsException, RestaurantNotFoundException;
    public List<Menu> getMenuByRestaurant(String city,String resName);
    public ResturantCity updateDetails(ResturantCity resturantCity,String city);
    public boolean deleteDetails(String city);
    public boolean deleteRestaurantByName(String city,String resName);
    public boolean deleteMenuByItemName(String city,String resName,String itemName);

    public ResturantCity updateRestaurantDetails(Resturant resturant,String city,String resName);
    public ResturantCity updateMenuList(Menu menu,String city,String resName,String itemName);
}