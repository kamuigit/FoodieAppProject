package com.niit.FoodieApp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Menu {

    @Id
    private String menuId;
    private String itemName;
    private String category;
    private float price;
    private int qty;
    private String rating;
    private String image;
    private String resName;
    private String resCity;
}
