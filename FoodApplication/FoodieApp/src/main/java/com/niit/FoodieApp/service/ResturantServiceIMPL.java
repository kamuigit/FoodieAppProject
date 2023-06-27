package com.niit.FoodieApp.service;

import com.niit.FoodieApp.domain.Menu;
import com.niit.FoodieApp.domain.Resturant;
import com.niit.FoodieApp.domain.ResturantCity;
import com.niit.FoodieApp.exception.*;
import com.niit.FoodieApp.repository.RestaurantRepositiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResturantServiceIMPL implements RestaurantService{
    RestaurantRepositiory restaurantRepositiory;
    @Autowired
    public ResturantServiceIMPL(RestaurantRepositiory restaurantRepositiory) {
        this.restaurantRepositiory = restaurantRepositiory;
    }
    @Override
    public ResturantCity saveCity(ResturantCity restaurantCity) throws CityAlreadyExistsException {
    if(restaurantRepositiory.findById(restaurantCity.getCity()).isPresent()){
        throw new CityAlreadyExistsException();
    }else{
        return restaurantRepositiory.save(restaurantCity);
    }
    }
    @Override
    public List<ResturantCity> getAllCities() {
        return restaurantRepositiory.findAll();
    }
    @Override
    public ResturantCity saveRestaurantInCity(String city, Resturant restaurant) throws RestaurantAlreadyExistsException, CityNotFoundException {
        if (restaurantRepositiory.findById(city).isEmpty()) {
            throw new CityNotFoundException();
        }
        ResturantCity rc = restaurantRepositiory.findById(city).get();
        List<Resturant> list = rc.getRestaurantList();
        if(list==null){}else{
        for (Resturant value : list) {
            if (value.getResName().equals(restaurant.getResName())) {
                throw new RestaurantAlreadyExistsException();
            }
        }}
        list.add(restaurant);
        rc.setRestaurantList(list);
        return restaurantRepositiory.save(rc);
    }

    @Override
    public List<Resturant> getAllRestaurantsByCity(String city) {
        return restaurantRepositiory.findById(city).get().getRestaurantList();
    }

    @Override
    public ResturantCity saveMenuToRestaurant(String city, String resName, Menu menu) throws CityNotFoundException, ItemAlreadyExistsException, RestaurantNotFoundException {
        if ( restaurantRepositiory.findById(city).isEmpty()) {
            throw new CityNotFoundException();
        }
        ResturantCity rc = restaurantRepositiory.findById(city).get();
        List<Resturant> list = rc.getRestaurantList();
        boolean flag = true;
        for (Resturant restaurant : list) {
            if (restaurant.getResName().equals(resName)) {
                flag = false;
            }
        }
        if (flag) {
            throw new RestaurantNotFoundException();
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getResName().equals(resName)) {
                Resturant r = list.get(i);
                List<Menu> m = r.getMenu();
                for (int j = 0; j < m.size(); j++) {
                    if (m.get(j).getItemName().equals(menu.getItemName())) {
                        throw new ItemAlreadyExistsException();
                    }
                }
                m.add(menu);
                r.setMenu(m);
            }
        }
        return restaurantRepositiory.save(rc);
    }
    @Override
    public List<Menu> getMenuByRestaurant(String city, String resName) {
        List<Resturant> list = restaurantRepositiory.findById(city).get().getRestaurantList();
        List<Menu> menu = null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getResName().equals(resName)) {
                menu = list.get(i).getMenu();
            }
        }
        return menu;
    }

    @Override
    public ResturantCity updateDetails(ResturantCity resturantCity, String city) {
        Optional<ResturantCity> checkRestaurant=restaurantRepositiory.findById(city);
        if (checkRestaurant.isEmpty()){
            return null;
        }
        ResturantCity existingRestaurant = checkRestaurant.get();
        if (resturantCity.getImage()!= null) {
            existingRestaurant.setImage(resturantCity.getImage());
        }
        return restaurantRepositiory.save(existingRestaurant);
    }

    @Override
    public boolean deleteDetails(String city) {
        if(restaurantRepositiory.findById(city).isEmpty()) {
            return false;
        }
        else {
            restaurantRepositiory.deleteById(city);
            return true;
        }
    }

    @Override
    public boolean deleteRestaurantByName(String city,String resName) {
        List<Resturant> restaurants = restaurantRepositiory.findById(city).get().getRestaurantList();
        for (int i = 0; i < restaurants.size(); i++) {
            if(restaurants.get(i).getResName().equals(resName)){
                restaurants.remove(i);
            }
        }
        ResturantCity resturantCity = new ResturantCity(city,restaurantRepositiory.findByCity(city).getImage(),restaurants);
        restaurantRepositiory.save(resturantCity);
        return true;
    }

    @Override
    public boolean deleteMenuByItemName(String city, String resName, String itemName) {
        List<Resturant> restaurants = restaurantRepositiory.findById(city).get().getRestaurantList();
        for (int i = 0; i < restaurants.size(); i++) {
            if(restaurants.get(i).getResName().equals(resName)){
                List<Menu> menuList = restaurants.get(i).getMenu();
//                System.out.println(menuList);
                for (int j = 0; j < menuList.size(); j++) {
                    if (menuList.get(j).getItemName().equals(itemName)){
                        menuList.remove(j);
                        System.out.println(menuList);
                    }
                }
                Resturant resturant = new Resturant(resName,restaurants.get(i).getImage(),menuList);
                restaurants.remove(i);
                restaurants.add(i,resturant);
                System.out.println(resturant);
            }
        }
        ResturantCity resturantCity = new ResturantCity(city,restaurantRepositiory.findByCity(city).getImage(),restaurants);
        restaurantRepositiory.save(resturantCity);
        return true;
    }

    @Override
    public ResturantCity updateRestaurantDetails(Resturant resturant,String city,String resName) {
        Optional<ResturantCity> checkRestaurant = restaurantRepositiory.findById(city);
        if(checkRestaurant.isPresent()){
            ResturantCity existingCity = checkRestaurant.get();
            List<Resturant> restaurantList = existingCity.getRestaurantList();
            for (Resturant existingRestaurant : restaurantList) {
                if (existingRestaurant.getResName().equals(resName)) {
                    if (resturant.getResName() != null) {
                        existingRestaurant.setResName(resturant.getResName());
                    }
                    if(resturant.getImage() != null) {
                        existingRestaurant.setImage(resturant.getImage());
                    }
                    if (resturant.getMenu() != null) {
                        existingRestaurant.setMenu(resturant.getMenu());
                    }
                    break;
                }
            }
            return restaurantRepositiory.save(existingCity);
        }
        else {
            return null;
        }
    }

    @Override
    public ResturantCity updateMenuList(Menu menu,String city,String resName, String itemName) {
        Optional<ResturantCity> checkRestaurant = restaurantRepositiory.findById(city);
        if(checkRestaurant.isPresent()){
            ResturantCity resturantCity = checkRestaurant.get();
            List<Resturant> resturantList = resturantCity.getRestaurantList();
            for(Resturant existingRestaurant : resturantList){
                List<Menu> menuList = existingRestaurant.getMenu();
                for (Menu menu1 : menuList){
                    if (menu1.getItemName().equals(itemName)) {
                        if (menu.getItemName() != null) {
                            menu1.setItemName(menu.getItemName());
                        }
                        if (menu.getMenuId() != null) {
                            menu1.setMenuId(menu.getMenuId());
                        }
                        if (menu.getCategory() != null) {
                            menu1.setCategory(menu.getCategory());
                        }
                        if (menu.getPrice() != 0) {
                            menu1.setPrice(menu.getPrice());
                        }
                        if (menu.getQty() != 0) {
                            menu1.setQty(menu.getQty());
                        }
                        if (menu.getImage() != null) {
                            menu1.setImage(menu.getImage());
                        }
                        if (menu.getResName() != null) {
                            menu1.setResName(menu.getResName());
                        }
                        if (menu.getResCity() != null) {
                            menu1.setResCity(menu.getResCity());
                        }
                    }
                }
            }
            return restaurantRepositiory.save(resturantCity);

        }
        else {
            return null;
        }
    }

}
