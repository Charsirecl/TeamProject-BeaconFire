package com.az.emailservice.controller;

import com.az.emailservice.enetity.EmailRequest;
import com.az.emailservice.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@Tag(name = "Email Service",description = "Send email")
public class EmailController {
    private final EmailService emailService;
    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping(value = "/send")
    @Operation(summary = "Send an email",description = "Sends an email")
//    @PreAuthorize("hasAuthority('HR')")
    public ResponseEntity<EmailRequest> sendEmail(@RequestBody EmailRequest message) {
        emailService.sendRegisterEmail(message);
        return ResponseEntity.ok(message);
    }
}
