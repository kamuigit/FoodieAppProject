package com.niit.FavouriteService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class UserFavourite {
    @Id
    private String email;
    private List<Restaurant> resturantList;
    private List<Menu> menuList;
}
