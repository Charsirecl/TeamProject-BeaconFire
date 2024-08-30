package com.az.authenticationservice.service;

import com.az.authenticationservice.domain.Role;
import com.az.authenticationservice.domain.User;
import com.az.authenticationservice.exception.UserNotFoundException;
import com.az.authenticationservice.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUser_success() throws UserNotFoundException {
        User mockUser = new User(
                23L,
                "kayk",
                "kay@example.com",
                "$10$KrDXHVhZUmooKqzaiQO6xOYphO1lUZqylm82s7tLyc6yM1jZNG9Vq",
                LocalDateTime.now(),
                true,
                new HashSet<>(),
                new HashSet<>()

        );

        Mockito.when(userRepo.getUserById(23)).thenReturn(mockUser);
        User result = userService.getUserById(23);
        assertEquals(mockUser,result);

    }

    @Test
    void testGetUser_failure() throws UserNotFoundException {
        Mockito.when(userRepo.getUserById(99)).thenReturn(null);
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(99));
    }

    @Test
    void testSaveUser_success() throws UserNotFoundException {
        User mockUser = new User(
                23L,
                "kayk",
                "kay@example.com",
                "$10$KrDXHVhZUmooKqzaiQO6xOYphO1lUZqylm82s7tLyc6yM1jZNG9Vq",
                LocalDateTime.now(),
                true,
                new HashSet<>(),
                new HashSet<>()

        );

        userService.saveUser(mockUser);

        Mockito.verify(userRepo).save(mockUser);
    }

    @Test
    void testGetAllUsers_success() throws UserNotFoundException {
        List<User> users = Mockito.mock(List.class);
        Mockito.when(userRepo.getAllUsers()).thenReturn(users);

        List<User> result = userService.getAllUsers();
        assertEquals(users,result);
    }

//    @Test
//    void testDeleteUser_success() throws UserNotFoundException {
//        User mockUser = new User(
//                23L,
//                "kayk",
//                "kay@example.com",
//                "$10$KrDXHVhZUmooKqzaiQO6xOYphO1lUZqylm82s7tLyc6yM1jZNG9Vq",
//                LocalDateTime.now(),
//                true,
//                new HashSet<>(),
//                new HashSet<>()
//
//        );
//
//        Mockito.when(userRepo.getUserById(23)).thenReturn(mockUser);
//
//        Mockito.doNothing().when(userRepo).deleteUserById(23);
//
//        userService.deleteUserById(23);
//        assertAll(()->userService.deleteUserById(23));
//    }


}
