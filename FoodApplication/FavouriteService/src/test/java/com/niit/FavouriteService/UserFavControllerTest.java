package com.niit.FavouriteService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.FavouriteService.controller.UserFavController;
import com.niit.FavouriteService.domain.Menu;
import com.niit.FavouriteService.domain.Restaurant;
import com.niit.FavouriteService.domain.UserFavourite;
import com.niit.FavouriteService.repository.UserFavRepository;
import com.niit.FavouriteService.service.UserFavouriteServiceIMPL;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserFavControllerTest {
    @Mock
    private UserFavouriteServiceIMPL userFavouriteServiceIMPL;
    @InjectMocks
    private UserFavController userFavController;
    @Autowired
    private MockMvc mockMvc;
    private Restaurant restaurant;
    private Menu menu;
    private UserFavourite userFavourite;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        List<Menu> menuList = new ArrayList<>();
        menuList.add(menu = new Menu("1", "pizza", "spicy", "200", "2","2.2", "pizza.jpg", "test", "latur"));
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant = new Restaurant("test", "test.jpg", menuList));
        userFavourite = new UserFavourite();
        userFavourite.setEmail("abc@gmail.com");
        userFavourite.setResturantList(restaurantList);
        userFavourite.setMenuList(menuList);
        mockMvc = MockMvcBuilders.standaloneSetup(userFavController).build();
    }

    @AfterEach
    void tearDown() {
        restaurant = null;
        menu = null;
        userFavourite=null;
    }

//    @Test
//    public void addRestaurantToFav() throws Exception {
//        when(userFavouriteServiceIMPL.addRestaurantToFavorites(userFavourite.getEmail(), restaurant)).thenReturn(userFavourite);
//
//        mockMvc.perform(post("/api/favouriteService/v1/addRestaurant/{email}", userFavourite.getEmail())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(convertToJson(restaurant)))
//                .andExpect(status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//        verify(userFavouriteServiceIMPL, times(1)).addRestaurantToFavorites(userFavourite.getEmail(), restaurant);
//    }
    @Test
    public void getALLFavRestaurantByEmail() throws Exception {
        when(userFavouriteServiceIMPL.getALLFavRestaurantByEmail(userFavourite.getEmail())).thenReturn(userFavourite.getResturantList());
        mockMvc.perform(
                get("/api/favouriteService/v1/resturants/{email}", userFavourite.getEmail())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertToJson(restaurant)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(userFavouriteServiceIMPL,times(1)).getALLFavRestaurantByEmail(userFavourite.getEmail());
    }

    @Test
    public void deleteRestaurantFromFavById() throws Exception {
       when(userFavouriteServiceIMPL.deleteRestaurantFromFavById(userFavourite.getEmail(),restaurant.getResName())).thenReturn(true);
       mockMvc.perform(
               delete("/api/favouriteService/v1/delete/{email}/{resName}", userFavourite.getEmail(),restaurant.getResName())
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(convertToJson(restaurant)))
               .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
       verify(userFavouriteServiceIMPL,times(1)).deleteRestaurantFromFavById(userFavourite.getEmail(),restaurant.getResName());
    }

//    @Test
//    public void addMenuToFav() throws Exception {
//        when(userFavouriteServiceIMPL.addMenuToFavorites(userFavourite.getEmail(),menu)).thenReturn(userFavourite);
//
//        mockMvc.perform(
//                post("/api/favouriteService/v1/addMenu/{email}", userFavourite.getEmail())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(convertToJson(menu)))
//                .andExpect(status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//        verify(userFavouriteServiceIMPL, times(1)).addMenuToFavorites(userFavourite.getEmail(),menu);
//    }
    @Test
    public void getALLFavMenuByEmail() throws Exception {
        when(userFavouriteServiceIMPL.getALLFavMenuByEmail(userFavourite.getEmail())).thenReturn(userFavourite.getMenuList());
        mockMvc.perform(
                        get("/api/favouriteService/v1/get-menu/{email}", userFavourite.getEmail())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(convertToJson(menu)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(userFavouriteServiceIMPL,times(1)).getALLFavMenuByEmail(userFavourite.getEmail());
    }

    @Test
    public void deleteMenuFromFavById() throws Exception {
        when(userFavouriteServiceIMPL.deleteMenuFromFavById(userFavourite.getEmail(),menu.getResName())).thenReturn(true);
        mockMvc.perform(
                        delete("/api/favouriteService/v1/delete-menu/{email}/{resName}", userFavourite.getEmail(),menu.getResName())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(convertToJson(menu)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(userFavouriteServiceIMPL,times(1)).deleteMenuFromFavById(userFavourite.getEmail(),menu.getResName());
    }
    public static String convertToJson(final Object object)
    {
        String result ="";
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
