package com.niit.FoodieApp.controller;


import com.niit.FoodieApp.domain.Menu;
import com.niit.FoodieApp.domain.ResturantCity;
import com.niit.FoodieApp.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/foodieservice")
public class MenuController {
    private MenuService menuService;
    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }
   //     http://localhost:8081/foodieservice/menu/save
    @PostMapping("/menu/save")
    public ResponseEntity<?> registerCustomer(HttpServletRequest httpServletRequest,@RequestBody Menu menu){
        if(httpServletRequest.getAttribute("attr2") != null && httpServletRequest.getAttribute("attr2").equals("Admin_Role")) {
            return new ResponseEntity<>(menuService.saveMenu(menu), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity("You are Not Authorized to access this link...",HttpStatus.BAD_REQUEST);
        }
    }
    //     http://localhost:8081/foodieservice/menu/all
    @GetMapping("/menu/all")
    public ResponseEntity<?> getAllMenu(){
        return new ResponseEntity<>( menuService.getAllFoods(), HttpStatus.CREATED);
    }
//               http://localhost:8081/foodieservice/update-menu/{menuId}    [put]
    @PutMapping("/update-menu/{menuId}")
    public ResponseEntity updateDetails(HttpServletRequest httpServletRequest, @RequestBody Menu menu, @PathVariable String menuId){
        String role = (String) httpServletRequest.getAttribute("attr2");
        if(role != null && role.equals("Admin_Role")) {
            return new ResponseEntity<>(menuService.updateDetails(menu,menuId),HttpStatus.OK);
        }
        else {
            return new ResponseEntity("You are Not Authorized to access this link...",HttpStatus.BAD_REQUEST);
        }
    }

    //            http://localhost:8081/foodieservice/delete-menu/{menuId}         [delete]
    @DeleteMapping("/delete-menu/{menuId}")
    public ResponseEntity deleteDetails(HttpServletRequest httpServletRequest,@PathVariable String menuId){
        String role = (String) httpServletRequest.getAttribute("attr2");
        if(role != null && role.equals("Admin_Role")) {
            return new ResponseEntity<>(menuService.deleteDetailsByMenu(menuId),HttpStatus.OK);
        }
        else {
            return new ResponseEntity("You are Not Authorized to access this link...",HttpStatus.BAD_REQUEST);
        }
    }


}
