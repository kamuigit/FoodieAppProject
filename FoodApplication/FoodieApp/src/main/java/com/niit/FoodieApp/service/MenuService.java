package com.niit.FoodieApp.service;

import com.niit.FoodieApp.domain.Menu;
import com.niit.FoodieApp.domain.ResturantCity;

import java.util.List;

public interface MenuService {

    public Menu saveMenu(Menu menu);

    public List<Menu> getAllFoods();
    public Menu updateDetails(Menu menu, String menuId);
    public boolean deleteDetailsByMenu(String menuId);



}
