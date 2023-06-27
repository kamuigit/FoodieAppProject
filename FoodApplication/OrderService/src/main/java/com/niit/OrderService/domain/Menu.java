package com.niit.OrderService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Menu {
    private String menuId;
    private String itemName;
    private String category;
    private float price;
    private String qty;

    private String image;
    private String resName;
    private String resCity;

}
