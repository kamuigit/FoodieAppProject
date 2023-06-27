package com.niit.FoodieApp.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Resturant {

    @Id
    private String resName;
    private String image;
    private List<Menu> menu;
}
