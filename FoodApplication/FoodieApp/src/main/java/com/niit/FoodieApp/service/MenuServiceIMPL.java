package com.niit.FoodieApp.service;

import com.niit.FoodieApp.domain.Menu;
import com.niit.FoodieApp.repository.MenuRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceIMPL implements MenuService{

    private MenuRespository menuRepository;

    @Autowired
    public MenuServiceIMPL(MenuRespository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public Menu saveMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public List<Menu> getAllFoods() {
        return menuRepository.findAll();
    }

    @Override
    public Menu updateDetails(Menu menu, String menuId) {
        Optional<Menu> checkMenu = menuRepository.findById(menuId);
        if (checkMenu.isEmpty()){
            return null;
        }
        Menu existingMenu = checkMenu.get();
        if (menu.getItemName() != null) {
            existingMenu.setPrice(menu.getPrice());
        }
        if (menu.getCategory() != null) {
            existingMenu.setCategory(menu.getCategory());
        }
        if (menu.getImage() != null){
            existingMenu.setImage(menu.getImage());
        }
        if (menu.getQty() != 0) {
            existingMenu.setQty(menu.getQty());
        }
        if (menu.getResName() != null){
            existingMenu.setResName(menu.getResName());
        }
        if (menu.getResCity() != null){
            existingMenu.setResCity(menu.getResCity());
        }
        return menuRepository.save(existingMenu);
    }

    @Override
    public boolean deleteDetailsByMenu(String menuId) {
        if(menuRepository.findById(menuId).isEmpty()) {
            return false;
        }
        else {
            menuRepository.deleteById(menuId);
            return true;
        }
    }
}
