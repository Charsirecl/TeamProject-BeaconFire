package com.az.authenticationservice.service;

import com.az.authenticationservice.domain.Role;
import com.az.authenticationservice.repository.RoleRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {
    @Mock
    private RoleRepo roleRepo;

    @InjectMocks
    private RoleService roleService;

    @Test
    void testGetRole(){
        Role role = new Role(
                1L,
                "HR",
                "Head Rotten",
                LocalDateTime.now(),
                LocalDateTime.now(),
                new HashSet<>()
        );

        Mockito.when(roleRepo.getRoleById(1)).thenReturn(role);
        Role result = roleService.GetRoleById(1);
        assertEquals(role,result);
    }

    @Test
    void testGetAllRoles(){
        List<Role> roles = Mockito.mock(List.class);
        Mockito.when(roleRepo.getAllRoles()).thenReturn(roles);

        List<Role> result = roleService.getAllRoles();
        assertEquals(roles,result);
    }

    @Test
    void testSaveRole(){
        Role role = new Role(
                1L,
                "HR",
                "Head Rotten",
                LocalDateTime.now(),
                LocalDateTime.now(),
                new HashSet<>()
        );

        roleService.saveRole(role);
        Mockito.verify(roleRepo, Mockito.times(1)).save(role);
    }

//    @Test
//    void testDeleteRole(){
//        Role role = new Role(
//                1L,
//                "HR",
//                "Head Rotten",
//                LocalDateTime.now(),
//                LocalDateTime.now(),
//                new HashSet<>()
//        );
//
//        Mockito.when(roleRepo.getRoleById(1)).thenReturn(role);
//        Mockito.doNothing().when(roleRepo).deleteRoleById(1);
//
//        roleService.deleteRoleById(1);
//        assertAll(()->roleService.deleteRoleById(1));
//    }

}
