package com.az.authenticationservice.repository;

import com.az.authenticationservice.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepo extends AbstractHibernateDao<Role> {
    public RoleRepo() {setClazz(Role.class);}

    public void saveRole(Role role) {
        save(role);
    }

    public List<Role> getAllRoles() {
        return this.getAll();
    }

    public Role getRoleById(int id) {
        return findById(id);
    }

    public void deleteRoleById(int id) {
        Role role = getRoleById(id);
        delete(role);
    }
}
