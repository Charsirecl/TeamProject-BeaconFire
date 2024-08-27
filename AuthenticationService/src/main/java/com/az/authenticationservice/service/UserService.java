package com.az.authenticationservice.service;

import com.az.authenticationservice.domain.Role;
import com.az.authenticationservice.domain.User;
import com.az.authenticationservice.exception.RoleNotFoundException;
import com.az.authenticationservice.exception.UserNotFoundException;
import com.az.authenticationservice.repository.RoleRepo;
import com.az.authenticationservice.repository.UserRepo;
import com.az.authenticationservice.security.AuthUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Autowired
    public UserService(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    public void saveUser(User user) {
        //Getting role id
        Set<Role> roles = new HashSet<>();
        //Getting roles
        for (Role role : user.getRoles()) {
            Role r = roleRepo.getRoleById((int) role.getId());
            if(r == null){
                throw new RoleNotFoundException("Role not found: " + role.getId());
            }
            roles.add(r);
        }
        //Encrypt the password
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        user.setRoles(roles);

        userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.getAllUsers();
    }

    public User getUserById(int id) {
        User user = userRepo.getUserById(id);
        if(user == null){
            throw new UserNotFoundException("User not found: " + id);
        }
        return user;
    }

    public void deleteUserById(int id) {
        User u = userRepo.getUserById(id);
        if(u == null){
            throw new UserNotFoundException("User not found: " + id);
        }
        userRepo.deleteUserById((int)u.getId());
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> u = userRepo.getUserByUsername(username);
        if(!u.isPresent()) {
            throw new UsernameNotFoundException(username+" not found");
        }

        User user = u.get();

        return AuthUserDetail.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(getAuthoritiesFromUser(user))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();


    }

    private List<GrantedAuthority> getAuthoritiesFromUser(User user){
        List<GrantedAuthority> userAuthorities = new ArrayList<>();

        for (Role role :  user.getRoles()){
            userAuthorities.add(new SimpleGrantedAuthority(role.getRolename()));
        }

        return userAuthorities;
    }
}
