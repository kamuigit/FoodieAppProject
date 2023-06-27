package com.niit.FoodieApp.Test;
import com.niit.FoodieApp.domain.Resturant;
import com.niit.FoodieApp.domain.ResturantCity;
import com.niit.FoodieApp.exception.CityAlreadyExistsException;
import com.niit.FoodieApp.repository.RestaurantRepositiory;
import com.niit.FoodieApp.service.RestaurantService;
import com.niit.FoodieApp.service.ResturantServiceIMPL;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

    @Mock
    private RestaurantRepositiory restaurantRepositiory;
    @InjectMocks
    private ResturantServiceIMPL restaurantService;
    private Resturant resturant;

    @BeforeEach
    public void setUp() {
         resturant= new Resturant();

    }

    @AfterEach
    private void tearDown() {
        resturant=null;
    }

    @Test
    public void saveCitySuccess() throws CityAlreadyExistsException {
        ResturantCity restaurantCity = new ResturantCity();
        restaurantCity.setCity("CityName");
        when(restaurantRepositiory.findById(restaurantCity.getCity())).thenReturn(Optional.empty());
        when(restaurantRepositiory.save(restaurantCity)).thenReturn(restaurantCity);
        ResturantCity savedCity = restaurantService.saveCity(restaurantCity);
        Assertions.assertEquals(restaurantCity, savedCity);
        verify(restaurantRepositiory, times(1)).findById(restaurantCity.getCity());
        verify(restaurantRepositiory, times(1)).save(restaurantCity);
    }

    @Test
    public void saveCityFailure() {
        ResturantCity restaurantCity = new ResturantCity();
restaurantCity.setCity("CityName");
        when(restaurantRepositiory.findById(restaurantCity.getCity())).thenReturn(Optional.of(restaurantCity));
        Assertions.assertThrows(CityAlreadyExistsException.class, () -> restaurantService.saveCity(restaurantCity));
        verify(restaurantRepositiory, times(1)).findById(restaurantCity.getCity());
        verify(restaurantRepositiory, times(0)).save(restaurantCity);
    }
    @Test
    public void saveRestaurantInCity_Success() throws Exception{
        Resturant restaurant = new Resturant();
        restaurant.setResName("RestaurantName");
        ResturantCity existingCity = new ResturantCity();
        existingCity.setCity("city");
        List<Resturant> existingRestaurants = new ArrayList<>();
        existingCity.setRestaurantList(existingRestaurants);
        when(restaurantRepositiory.findById("city")).thenReturn(Optional.of(existingCity));
        when(restaurantRepositiory.save(existingCity)).thenReturn(existingCity);
        ResturantCity savedCity = restaurantService.saveRestaurantInCity("city", restaurant);
    }
    @Test
    public void getAllRestaurantsByCitySuccess() {
        Resturant restaurant1 = new Resturant();
        restaurant1.setResName("Restaurant1");
        Resturant restaurant2 = new Resturant();
        restaurant2.setResName("Restaurant2");
        ResturantCity existingCity = new ResturantCity();
        existingCity.setCity("city");
        List<Resturant> existingRestaurants = new ArrayList<>();
        existingRestaurants.add(restaurant1);
        existingRestaurants.add(restaurant2);
        existingCity.setRestaurantList(existingRestaurants);
        when(restaurantRepositiory.findById("city")).thenReturn(Optional.of(existingCity));
        List<Resturant> restaurants = restaurantService.getAllRestaurantsByCity("city");
        Assertions.assertEquals(existingRestaurants, restaurants);
        verify(restaurantRepositiory, times(1)).findById("city");
    }
    @Test
    public void updateDetails_ExistingCity_Successful() {
        String city = "CityName";
        ResturantCity existingCity = new ResturantCity(city, "ExistingImage", new ArrayList<>());
        ResturantCity updatedCity = new ResturantCity(city, "UpdatedImage", new ArrayList<>());
        when(restaurantRepositiory.findById(city)).thenReturn(Optional.of(existingCity));
        when(restaurantRepositiory.save(existingCity)).thenReturn(updatedCity);
        ResturantCity result = restaurantService.updateDetails(updatedCity, city);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(updatedCity.getImage(), result.getImage());
        verify(restaurantRepositiory, times(1)).findById(city);
        verify(restaurantRepositiory, times(1)).save(existingCity);
    }
    @Test
    public void deleteRestaurantByNameForSuccess() {
        String city = "CityName";
        String restaurantName = "RestaurantName";
        Resturant restaurant1 = new Resturant();
        Resturant restaurant2 = new Resturant();
        restaurant1.setResName(restaurantName+"1");
        restaurant2.setResName(restaurantName+"2");
        List<Resturant> existingRestaurants = new ArrayList<>();
        existingRestaurants.add(restaurant1);
        existingRestaurants.add(restaurant2);
        ResturantCity existingCity = new ResturantCity(city, "CityImage", existingRestaurants);
        when(restaurantRepositiory.findById(city)).thenReturn(Optional.of(existingCity));
        when(restaurantRepositiory.findByCity(city)).thenReturn(existingCity);
        when(restaurantRepositiory.save(existingCity)).thenReturn(existingCity);
        boolean isDeleted = restaurantService.deleteRestaurantByName(city, restaurantName);
        Assertions.assertTrue(isDeleted);
        Assertions.assertEquals(2, existingCity.getRestaurantList().size());
        verify(restaurantRepositiory, times(1)).findById(city);
        verify(restaurantRepositiory, times(1)).findByCity(city);
        verify(restaurantRepositiory, times(1)).save(existingCity);
    }

    @Test
    public void deleteDetailsForSuccess() {
        String city = "ExistingCity";
        when(restaurantRepositiory.findById(city)).thenReturn(Optional.of(new ResturantCity(city, "Image", null)));
        boolean result = restaurantService.deleteDetails(city);
        Assertions.assertTrue(result);
        verify(restaurantRepositiory, times(1)).findById(city);
        verify(restaurantRepositiory, times(1)).deleteById(city);
    }

    @Test
    public void deleteDetails_NonExistingCityFailure() {
        String city = "NonExistingCity";
        when(restaurantRepositiory.findById(city)).thenReturn(Optional.empty());
        boolean result = restaurantService.deleteDetails(city);
        Assertions.assertFalse(result);
        verify(restaurantRepositiory, times(1)).findById(city);
        verify(restaurantRepositiory, times(0)).deleteById(city);
    }
}
