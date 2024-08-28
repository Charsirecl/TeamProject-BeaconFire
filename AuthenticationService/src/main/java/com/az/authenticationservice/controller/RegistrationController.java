package com.az.authenticationservice.controller;

import com.az.authenticationservice.domain.RegistrationToken;
import com.az.authenticationservice.service.RegistrationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegistrationController {
    private RegistrationTokenService registrationTokenService;

    @Autowired
    public void setRegistrationTokenService(RegistrationTokenService registrationTokenService) {
        this.registrationTokenService = registrationTokenService;
    }

    @PostMapping(value = "/token")
    @PreAuthorize("hasAuthority('HR')")
    public ResponseEntity<RegistrationToken> saveRegistertoken(@RequestBody RegistrationToken registrationToken) {
        registrationTokenService.save(registrationToken);
        return ResponseEntity.ok(registrationToken);
    }



}
