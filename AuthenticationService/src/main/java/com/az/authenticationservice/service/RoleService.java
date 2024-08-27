package com.az.authenticationservice.service;

import com.az.authenticationservice.domain.Role;
import com.az.authenticationservice.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleService {
    private final RoleRepo roleRepo;

    @Autowired
    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public void saveRole(Role role) {
        roleRepo.save(role);
    }

    public List<Role> getAllRoles() {
        return roleRepo.getAllRoles();
    }

    public Role GetRoleById(int id) {
        return roleRepo.getRoleById(id);
    }

    public void deleteRoleById(int id) {
        roleRepo.deleteRoleById(id);
    }
}
