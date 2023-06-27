package com.niit.FavouriteService;

import com.niit.FavouriteService.domain.Menu;
import com.niit.FavouriteService.domain.Restaurant;
import com.niit.FavouriteService.domain.UserFavourite;
import com.niit.FavouriteService.exception.NoItemInFavourite;
import com.niit.FavouriteService.repository.UserFavRepository;
import com.niit.FavouriteService.service.UserFavouriteServiceIMPL;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserFAvServiceTest {
    @Mock
    private UserFavRepository userFavRepository;
    @InjectMocks
    private UserFavouriteServiceIMPL userFavouriteServiceIMPL;
    private Restaurant restaurant;
    private Menu menu;
    private UserFavourite userFavourite;

    @BeforeEach
    void setUp() {
        List<Menu> menuList = new ArrayList<>();
        menuList.add(menu = new Menu("1", "pizza", "spicy", "200", "2","2.6", "pizza.jpg", "test", "latur"));
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant = new Restaurant("test", "test.jpg", menuList));
        userFavourite = new UserFavourite();
        userFavourite.setEmail("abc@gmail.com");
        userFavourite.setResturantList(restaurantList);
        userFavourite.setMenuList(menuList);
    }

    @AfterEach
    void tearDown() {
        restaurant = null;
        menu = null;
        userFavourite=null;
    }
//    @Test
//    void AddItemToFavouriteSuccess() {
//        when(userFavRepository.findById(userFavourite.getEmail())).thenReturn(Optional.ofNullable(null));
//        when(userFavRepository.save(any(UserFavourite.class))).thenAnswer(invocation -> invocation.getArgument(0));

//        UserFavourite insertRestaurant = userFavouriteServiceIMPL.addRestaurantToFavorites(userFavourite.getEmail(), restaurant);
//        System.out.println(insertRestaurant);
//        assertNotNull(insertRestaurant);
//        List<Restaurant> restaurantList = insertRestaurant.getResturantList();
//        assertEquals(1, restaurantList.size());
//        assertTrue(restaurantList.contains(restaurant));
//        System.out.println(restaurantList);
//
//        verify(userFavRepository, times(1)).findById(userFavourite.getEmail());
//        verify(userFavRepository, times(1)).save(any(UserFavourite.class));
//    }

//    @Test
//    void getALLFavRestaurantByEmail() throws NoItemInFavourite {
//        when(userFavRepository.findById(userFavourite.getEmail())).thenReturn(Optional.of(userFavourite));
//        List<Restaurant> result = userFavouriteServiceIMPL.getALLFavRestaurantByEmail(userFavourite.getEmail());
//        assertNotNull(result);
//        System.out.println(result);
//        assertEquals(1, result.size());
//        verify(userFavRepository, times(1)).findById(userFavourite.getEmail());
//    }0.


    @Test
    void deleteRestaurantFromFavById(){
        when(userFavRepository.findById(userFavourite.getEmail())).thenReturn(Optional.of(userFavourite));
        boolean result = userFavouriteServiceIMPL.deleteRestaurantFromFavById(userFavourite.getEmail(), restaurant.getResName());
        assertTrue(result);
        List<Restaurant> restaurantList = userFavourite.getResturantList();
        assertEquals(0, restaurantList.size());
        verify(userFavRepository, times(1)).findById(userFavourite.getEmail());
        verify(userFavRepository, times(1)).save(userFavourite);
    }

//    @Test
//    void testAddMenuToFavorites() {
//        when(userFavRepository.findById(userFavourite.getEmail())).thenReturn(Optional.ofNullable(null));
//        when(userFavRepository.save(any(UserFavourite.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//        UserFavourite insertMenu = userFavouriteServiceIMPL.addMenuToFavorites(userFavourite.getEmail(), menu);
//        System.out.println(insertMenu);
//        assertNotNull(insertMenu);
//        List<Menu> menuList = insertMenu.getMenuList();
//        assertEquals(1, menuList.size());
//        assertTrue(menuList.contains(menu));
//        System.out.println(menuList);
//
//        verify(userFavRepository, times(1)).findById(userFavourite.getEmail());
//        verify(userFavRepository, times(1)).save(any(UserFavourite.class));
//    }

    @Test
    void getALLFavMenuByEmail(){
        when(userFavRepository.findById(userFavourite.getEmail())).thenReturn(Optional.of(userFavourite));
        List<Menu> result = userFavouriteServiceIMPL.getALLFavMenuByEmail(userFavourite.getEmail());
        assertNotNull(result);
        System.out.println(result);
        assertEquals(1, result.size());
        verify(userFavRepository, times(1)).findById(userFavourite.getEmail());
    }

    @Test
    void deleteMenuFromFavById(){
        when(userFavRepository.findById(userFavourite.getEmail())).thenReturn(Optional.of(userFavourite));
        boolean result = userFavouriteServiceIMPL.deleteMenuFromFavById(userFavourite.getEmail(), restaurant.getResName());
        assertTrue(result);
        List<Menu> menuList = userFavourite.getMenuList();
        assertEquals(0, menuList.size());
        System.out.println(menuList);
        verify(userFavRepository, times(1)).findById(userFavourite.getEmail());
        verify(userFavRepository, times(1)).save(userFavourite);
    }
    }
