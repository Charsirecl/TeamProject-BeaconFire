package com.az.authenticationservice.service;

import com.az.authenticationservice.domain.RegistrationToken;
import com.az.authenticationservice.domain.User;
import com.az.authenticationservice.exception.TokenNotFoundException;
import com.az.authenticationservice.exception.UserNotFoundException;
import com.az.authenticationservice.repository.RegistrationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RegistrationTokenService {
    private final RegistrationTokenRepo registrationTokenRepo;
    private final EmailService emailService;
    private final UserService userService;

    @Autowired
    public RegistrationTokenService(RegistrationTokenRepo registrationTokenRepo, EmailService emailService, UserService userService) {
        this.registrationTokenRepo = registrationTokenRepo;
        this.emailService = emailService;
        this.userService = userService;
    }

    public void save(RegistrationToken registrationToken) {
        //Getting user id
        User u = userService.getUserById((int)registrationToken.getUser().getId());
        if (u == null){
            throw new UserNotFoundException("User not found: "+registrationToken.getUser().getId());
        }
        registrationToken.setUser(u);

        registrationTokenRepo.save(registrationToken);
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

    public RegistrationToken getLastRegistrationToken() {
        return registrationTokenRepo.getLastRegistrationToken();
    }
}
