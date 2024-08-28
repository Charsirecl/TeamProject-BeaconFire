package com.az.authenticationservice.controller;

import com.az.authenticationservice.domain.RegistrationToken;
import com.az.authenticationservice.domain.User;
import com.az.authenticationservice.exception.EmailAlreadyExistsException;
import com.az.authenticationservice.exception.EmailNotMatchException;
import com.az.authenticationservice.exception.TokenExpiredException;
import com.az.authenticationservice.exception.TokenNotFoundException;
import com.az.authenticationservice.service.RegistrationTokenService;
import com.az.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/register")
public class RegistrationController {
    private RegistrationTokenService registrationTokenService;
    private UserService userService;

    @Autowired
    public void setRegistrationTokenService(RegistrationTokenService registrationTokenService) {
        this.registrationTokenService = registrationTokenService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/token")
    @PreAuthorize("hasAuthority('HR')")
    public ResponseEntity<RegistrationToken> saveRegistertoken(@RequestBody RegistrationToken registrationToken) {
        registrationTokenService.save(registrationToken);
        return ResponseEntity.ok(registrationToken);
    }

    @PostMapping(value = "/user")
    @PreAuthorize("hasAuthority('Register')")
    public ResponseEntity<User> saveUser(@RequestBody User user, @RequestParam String token) {
        //get the token
        Optional<RegistrationToken> t = registrationTokenService.getAllRegistrationTokens().stream()
                .filter(registrationToken -> registrationToken.getToken().equals(token))
                .findFirst();

        RegistrationToken registrationToken = t.orElseThrow(()-> new TokenNotFoundException("Invalid token"));
        //check if email exists
        String email = user.getEmail();
        boolean emailExists = userService.getAllUsers().stream().anyMatch(user1 -> user1.getEmail().equalsIgnoreCase(email));
        boolean expire = registrationToken.getExpirationdate().isBefore(LocalDateTime.now());
        if(expire){
            throw new TokenExpiredException("Token expired");
        }else if(emailExists){
            throw new EmailAlreadyExistsException("Email already exists: "+email);
        }else{
            if(!registrationToken.getEmail().equals(user.getEmail())){
                throw new EmailNotMatchException("Email not match with token: "+registrationToken.getEmail());
            }
            userService.saveUser(user);

            //deactivate token
            registrationTokenService.deactivateRegistrationToken(registrationToken);
            return ResponseEntity.ok(user);
        }

    }



}
