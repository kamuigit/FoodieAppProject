package com.niit.FavouriteService.service;

import com.niit.FavouriteService.domain.Menu;
import com.niit.FavouriteService.domain.Restaurant;
import com.niit.FavouriteService.domain.UserFavourite;
import com.niit.FavouriteService.exception.NoItemInFavourite;
import com.niit.FavouriteService.repository.RestaurantRepository;
import com.niit.FavouriteService.repository.UserFavRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserFavouriteServiceIMPL implements  UserFavouriteService{
    private UserFavRepository userFavRepository;
    private RestaurantRepository restaurantRepository;
    @Autowired
    public UserFavouriteServiceIMPL(UserFavRepository userFavRepository, RestaurantRepository restaurantRepository) {
        this.userFavRepository = userFavRepository;
        this.restaurantRepository = restaurantRepository;
    }




    @Override
    public boolean addRestaurantToFavorites(String email, Restaurant restaurant) {
        UserFavourite user = null;
        boolean flag = true;
        Optional<UserFavourite> userOptional = userFavRepository.findById(email);

        if (userOptional.isEmpty()) {
            user = new UserFavourite();
            user.setEmail(email);
            List<Restaurant> reslist = new ArrayList<>();
            reslist.add(restaurant);
            user.setResturantList(reslist);
            List<Menu> menulist = new ArrayList<>();
            user.setMenuList(menulist);

        } else {
            user = userOptional.get();
            for (Restaurant res : user.getResturantList()) {
                if (res.getResName().equals(restaurant.getResName())) {
                    flag = false;
                }
            }
            if (flag) {
                user.getResturantList().add(restaurant);
            }
            else{
                user.getResturantList().remove(restaurant);
            }
        }
        userFavRepository.save(user);
        return flag;
    }

    @Override
    public List<Restaurant> getALLFavRestaurantByEmail(String email) throws NoItemInFavourite {
        if(userFavRepository.findById(email).isPresent()) {
            UserFavourite user = userFavRepository.findById(email).get();
            return user.getResturantList();
        }
        else{
            throw new NoItemInFavourite();
        }
    }

    @Override
    public boolean deleteRestaurantFromFavById(String email, String resName) {
        Optional<UserFavourite> userOptional = userFavRepository.findById(email);
        if (!userOptional.isPresent()) {
            return false;
        }
        UserFavourite user = userOptional.get();
        Optional<Restaurant> optionalRestaurant = user.getResturantList().stream()
                .filter(restaurant -> restaurant.getResName().equals(resName))
                .findFirst();

        if (!optionalRestaurant.isPresent()) {
            return false;
        }
        Restaurant restaurant = optionalRestaurant.get();
        user.getResturantList().remove(restaurant);
        userFavRepository.save(user);
        return true;
    }

    @Override
    public boolean addMenuToFavorites(String email, Menu menu) {
        UserFavourite user = null;
        boolean flag = true;
        Optional<UserFavourite> userOptional = userFavRepository.findById(email);

        if (userOptional.isEmpty()) {

            user = new UserFavourite();
            user.setEmail(email);
            List<Menu> menuList = new ArrayList<>();
            menuList.add(menu);
            user.setMenuList(menuList);
            List<Restaurant> restaurantList = new ArrayList<>();
            user.setResturantList(restaurantList);
        } else {
            user = userOptional.get();
            for (Menu menu1 : user.getMenuList()) {
                if (menu1.getResName().equals(menu.getResName())) {
                    flag = false;
                }
            }
            if (flag) {
                user.getMenuList().add(menu);
            }
            else{
                user.getMenuList().remove(menu);
            }
        }

        userFavRepository.save(user);
        return true;
    }


    @Override
    public List<Menu> getALLFavMenuByEmail(String email) {
        UserFavourite user=userFavRepository.findById(email).get();
        return user.getMenuList();
    }

    @Override
    public boolean deleteMenuFromFavById(String email, String resName) {
        Optional<UserFavourite> userOptional = userFavRepository.findById(email);
        if (!userOptional.isPresent()) {
            return false;
        }
        UserFavourite user = userOptional.get();
        Optional<Menu> optionalRestaurant = user.getMenuList().stream()
                .filter(menu -> menu.getResName().equals(resName))
                .findFirst();

        if (!optionalRestaurant.isPresent()) {
            return false;
        }
        Menu restaurant = optionalRestaurant.get();
        user.getMenuList().remove(restaurant);
        userFavRepository.save(user);
        return true;
    }
}
