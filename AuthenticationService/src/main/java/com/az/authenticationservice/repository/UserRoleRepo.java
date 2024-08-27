package com.az.authenticationservice.repository;

import com.az.authenticationservice.domain.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRoleRepo extends AbstractHibernateDao<UserRole>{
    public UserRoleRepo() {setClazz(UserRole.class);}

    public void saveUserRole(UserRole userRole) {
        save(userRole);
    }

    public List<UserRole> getAllUserRoles() {
        return this.getAll();
    }

    public UserRole getUserRoleById(int id) {
        return findById(id);
    }

    public void deleteUserRoleById(int id) {
        UserRole userRole = getUserRoleById(id);
        delete(userRole);
    }
}
