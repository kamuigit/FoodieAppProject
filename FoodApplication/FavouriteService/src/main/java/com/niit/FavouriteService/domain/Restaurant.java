package com.niit.FavouriteService.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Restaurant {

    @Id
    private String resName;
    private String image;
    private List<Menu> menu;
}
