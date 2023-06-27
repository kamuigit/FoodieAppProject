package com.niit.AuthApp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.AuthApp.controller.UserController;
import com.niit.AuthApp.domain.User;
import com.niit.AuthApp.service.SecurityTokenGenerator;
import com.niit.AuthApp.service.UserService;
import com.niit.AuthApp.service.UserServiceImpl;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserServiceImpl userServiceImpl;
    @InjectMocks
    private UserController userController;
    @Mock
    private SecurityTokenGenerator securityTokenGenerator;
    @Autowired
    private MockMvc mockMvc;
    private User user;
    @BeforeEach
    void setUp() {
        user = new User("abc@gmail.com","abc@123","User_Role");
        String token = "generated_token";
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
    @AfterEach
    void tearDown() {
        user = null;
    }
    @Test
    public void registerAccount() throws Exception {
        when(userServiceImpl.registerAccount(user)).thenReturn(user);
        mockMvc.perform(
                post("/api/user/v1/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(user)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        verify(userServiceImpl,times(1)).registerAccount(user);
    }

    @Test
    public void loginUser() throws Exception {
        when(userServiceImpl.checkUserLogin(user)).thenReturn(user);
        mockMvc.perform(
                        post("/api/user/v1/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(convertToJson(user)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(userServiceImpl,times(1)).checkUserLogin(user);
    }

    @Test
    public void getUserByEmail() throws Exception {
        when(userServiceImpl.findByEmail(user.getEmail())).thenReturn(user);
        mockMvc.perform(
                        get("/api/user/v1/get-User/{email}", user.getEmail())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(convertToJson(user)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(userServiceImpl, times(1)).findByEmail(user.getEmail());
    }

        public static String convertToJson ( final Object object)
        {
            String result = "";
            ObjectMapper mapper = new ObjectMapper();
            try {
                result = mapper.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return result;
        }
}
