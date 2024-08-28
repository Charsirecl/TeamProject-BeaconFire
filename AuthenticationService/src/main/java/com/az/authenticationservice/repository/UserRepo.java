package com.az.authenticationservice.repository;

import com.az.authenticationservice.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepo extends AbstractHibernateDao<User> {
    public UserRepo() {setClazz(User.class);}

    public void saveUser(User user) {
        save(user);
    }

    public List<User> getAllUsers(){
        return this.getAll();
    }

    public User getUserById(int id) {
        return findById(id);
    }

    public void deleteUserById(int id) {
        User user = getUserById(id);
        delete(user);
    }

    public Optional<User> getUserByUsername(String username) {
        return getAllUsers().stream()
                .filter(user ->username.equals(user.getEmail())).findAny();

    }
}
