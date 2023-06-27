package com.niit.FavouriteService.service;

import com.niit.FavouriteService.domain.Menu;
import com.niit.FavouriteService.domain.Restaurant;
import com.niit.FavouriteService.domain.UserFavourite;
import com.niit.FavouriteService.exception.NoItemInFavourite;

import java.util.List;

public interface UserFavouriteService {
    public boolean addRestaurantToFavorites(String email, Restaurant restaurant);
    public List<Restaurant> getALLFavRestaurantByEmail(String email) throws NoItemInFavourite;
    public boolean deleteRestaurantFromFavById(String email, String resName);

    public boolean addMenuToFavorites(String email, Menu menu);
    public List<Menu> getALLFavMenuByEmail(String email);
    public boolean deleteMenuFromFavById(String email, String menuId);
}
