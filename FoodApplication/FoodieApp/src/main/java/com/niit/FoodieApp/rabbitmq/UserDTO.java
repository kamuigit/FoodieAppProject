package com.niit.FoodieApp.rabbitmq;

import com.niit.FoodieApp.domain.Menu;
import com.niit.FoodieApp.domain.Resturant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserDTO {
    private String email;
    private Resturant restaurant;
    private Menu menu;
}
