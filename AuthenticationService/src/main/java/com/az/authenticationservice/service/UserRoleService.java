package com.az.authenticationservice.service;

import com.az.authenticationservice.domain.UserRole;
import com.az.authenticationservice.repository.UserRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserRoleService {
    private final UserRoleRepo userRoleRepo;

    @Autowired
    public UserRoleService(UserRoleRepo userRoleRepo) {
        this.userRoleRepo = userRoleRepo;
    }

    public void saveUserRole(UserRole userRole) {
        userRoleRepo.save(userRole);
    }

    public List<UserRole> getAllUserRoles() {
        return userRoleRepo.getAllUserRoles();
    }

    public UserRole getUserRoleById(int id) {
        return userRoleRepo.getUserRoleById(id);
    }

    public void deleteUserRoleById(int id) {
        userRoleRepo.deleteUserRoleById(id);
    }
}
