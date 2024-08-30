package com.az.authenticationservice.service;

import com.az.authenticationservice.domain.Role;
import com.az.authenticationservice.domain.User;
import com.az.authenticationservice.domain.UserRole;
import com.az.authenticationservice.repository.UserRoleRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserRoleServiceTest {
    @Mock
    private UserRoleRepo userRoleRepo;

    @InjectMocks
    private UserRoleService userRoleService;

    @Test
    void testGetUserRole() {
        UserRole userRole = new UserRole(
                1L,
                true,
                LocalDateTime.now(),
                LocalDateTime.now(),
                new User(),
                new Role()
        );

        Mockito.when(userRoleRepo.getUserRoleById(1)).thenReturn(userRole);
        UserRole userRole1 = userRoleService.getUserRoleById(1);
        assertEquals(userRole, userRole1);

    }

    @Test
    void testGetAllUserRoles() {
        List<UserRole> userRoles = Mockito.mock(List.class);
        Mockito.when(userRoleRepo.getAllUserRoles()).thenReturn(userRoles);

        List<UserRole> userRoles1 = userRoleService.getAllUserRoles();
        assertEquals(userRoles, userRoles1);
    }

    @Test
    void testSaveUserRole() {
        UserRole userRole = new UserRole(
                1L,
                true,
                LocalDateTime.now(),
                LocalDateTime.now(),
                new User(),
                new Role()
        );

        userRoleService.saveUserRole(userRole);
        Mockito.verify(userRoleRepo).save(userRole);
    }

//    @Test
//    void testDeleteUserRole() {
//        UserRole userRole = new UserRole(
//                1L,
//                true,
//                LocalDateTime.now(),
//                LocalDateTime.now(),
//                new User(),
//                new Role()
//        );
//
//        Mockito.when(userRoleRepo.getUserRoleById(1)).thenReturn(userRole);
//        Mockito.doNothing().when(userRoleRepo).deleteUserRoleById(1);
//        userRoleService.deleteUserRoleById(1);
//        assertAll(()->userRoleService.deleteUserRoleById(1));
//    }
}
