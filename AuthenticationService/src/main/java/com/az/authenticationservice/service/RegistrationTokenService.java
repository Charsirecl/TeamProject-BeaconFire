package com.az.authenticationservice.service;

import com.az.authenticationservice.domain.RegistrationToken;
import com.az.authenticationservice.domain.User;
import com.az.authenticationservice.utils.*;
import com.az.authenticationservice.exception.TokenNotFoundException;
import com.az.authenticationservice.exception.UserNotFoundException;
import com.az.authenticationservice.repository.RegistrationTokenRepo;
import com.az.authenticationservice.security.AuthUserDetail;
import com.az.authenticationservice.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class RegistrationTokenService {
    private final RegistrationTokenRepo registrationTokenRepo;
    private final EmailService emailService;
    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Autowired
    public RegistrationTokenService(RegistrationTokenRepo registrationTokenRepo, EmailService emailService, UserService userService, JwtProvider jwtProvider) {
        this.registrationTokenRepo = registrationTokenRepo;
        this.emailService = emailService;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }


    public void save(RegistrationToken registrationToken) {
        //Getting user id
        User u = userService.getUserById((int)registrationToken.getUser().getId());
        if (u == null){
            throw new UserNotFoundException("User not found: "+registrationToken.getUser().getId());
        }
        registrationToken.setUser(u);

        //Generating a token for register
        AuthUserDetail register = AuthUserDetail.builder()
                .username(registrationToken.getEmail()+new Random().nextInt())
                .password(" ")
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("Register")))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();

        registrationToken.setToken(jwtProvider.createToken(register));
        registrationTokenRepo.saveRegistrationToken(registrationToken);
        emailService.sendRegisterEmail(registrationToken.getEmail(), registrationToken.getToken());
    }

    public List<RegistrationToken> getAllRegistrationTokens() {
        return registrationTokenRepo.getAllRegistrationTokens();
    }

    public RegistrationToken getRegistrationTokenById(int id) {
        RegistrationToken t = registrationTokenRepo.getRegistrationTokenById(id);
        if(t == null) {
            throw new TokenNotFoundException("Token not found: "+ id);
        }
        return t;
    }

    public void deactivateRegistrationToken(RegistrationToken registrationToken) {
        registrationToken.setExpirationdate(LocalDateTime.now());
        registrationTokenRepo.detach(registrationToken);
        registrationTokenRepo.merge(registrationToken);

    }

}
