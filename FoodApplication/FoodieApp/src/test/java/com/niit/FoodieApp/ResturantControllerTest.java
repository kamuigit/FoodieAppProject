package com.niit.FoodieApp.Test;




import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.FoodieApp.controller.ResturantController;
import com.niit.FoodieApp.domain.Menu;
import com.niit.FoodieApp.domain.Resturant;
import com.niit.FoodieApp.domain.ResturantCity;
import com.niit.FoodieApp.service.ResturantServiceIMPL;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ResturantControllerTest {
    @Mock
    private ResturantServiceIMPL resturantServiceIMPL;
    @InjectMocks
    private ResturantController resturantController;

    @Autowired
    private MockMvc mockMvc;

    private ResturantCity resturant;
    Menu menu;

    @BeforeEach
    public void setUp() {
        resturant = new ResturantCity();
        mockMvc = MockMvcBuilders.standaloneSetup(resturantController).build();
    }

    @AfterEach
    private void tearDown() {
        resturant = null;
    }

    public static String convertToJson(final Object object) {
        String result = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Test
    public void saveCityAdminRoleSucess() throws Exception {
        when(resturantServiceIMPL.saveCity(resturant)).thenReturn(resturant);
        mockMvc.perform(MockMvcRequestBuilders.post("/food/resturant/add/city")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertToJson(resturant))
                        .requestAttr("attr2", "Admin_Role"))
                .andExpect(status().isCreated());
        verify(resturantServiceIMPL, times(1)).saveCity(resturant);
    }

    @Test
    public void saveCityNonAdminRoleFailure() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/food/resturant/add/city")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertToJson(resturant))
                        .requestAttr("attr2", "Some_Other_Role"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        verify(resturantServiceIMPL, times(0)).saveCity(resturant);
    }

}


