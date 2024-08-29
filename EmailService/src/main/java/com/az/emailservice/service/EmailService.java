package com.az.emailservice.service;

import com.az.emailservice.enetity.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private JavaMailSender mailSender;
    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendRegisterEmail(EmailRequest emailRequest){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailRequest.getRecipient());
        message.setSubject(emailRequest.getSubject());
        message.setText(emailRequest.getBody());
//        message.setText("Thank you for registering. Please use the following token to complete your registration: " + token);
        mailSender.send(message);
    }
}
