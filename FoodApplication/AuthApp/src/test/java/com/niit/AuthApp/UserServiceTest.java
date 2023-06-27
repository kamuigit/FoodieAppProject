package com.niit.AuthApp;

import com.niit.AuthApp.domain.User;
import com.niit.AuthApp.repository.UserRepository;
import com.niit.AuthApp.service.UserService;
import com.niit.AuthApp.service.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
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

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();

    }

    @AfterEach
    private void tearDown() {
        user = null;
    }

    @Test
    public void testCheckUserLoginSuccess() {
        when(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()))
                .thenReturn(user);
        User result = userService.checkUserLogin(user);
        Assertions.assertEquals(user, result);
        verify(userRepository, times(1)).findByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    @Test
    public void testRegisterAccountSuccess() {
        when(userRepository.save(user)).thenReturn(user);
        User result = userService.registerAccount(user);
        Assertions.assertEquals(user, result);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testFindByEmailSuccess() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        User result = userService.findByEmail(user.getEmail());
        Assertions.assertEquals(user, result);
        verify(userRepository, times(1)).findByEmail(user.getEmail());
    }
}

