package com.niit.FavouriteService.subscribe;

import com.niit.FavouriteService.domain.Menu;
import com.niit.FavouriteService.domain.Restaurant;
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
    private Restaurant restaurant;
    private Menu menu;
}
