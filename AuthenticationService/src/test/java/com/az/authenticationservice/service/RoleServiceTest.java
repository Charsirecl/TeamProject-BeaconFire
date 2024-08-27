package com.az.authenticationservice.service;

import com.az.authenticationservice.repository.RoleRepo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {
    @Mock
    private RoleRepo roleRepo;

    @InjectMocks
    private RoleService roleService;

}
