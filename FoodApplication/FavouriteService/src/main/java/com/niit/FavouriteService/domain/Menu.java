package com.niit.FavouriteService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Menu {

    @Id
    private String menuId;
    private String itemName;
    private String category;
    private String price;
    private String qty;
    private String rating;
    private String image;
    private String resName;
    private String resCity;

    public Menu(String menuId) {
        this.menuId = menuId;
    }

}
