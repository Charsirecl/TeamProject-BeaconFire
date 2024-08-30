package com.az.emailservice.service;

import com.az.emailservice.enetity.EmailRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class emailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailService emailService;

    @Test
    void testSendEmail() throws MessagingException {

        EmailRequest e = EmailRequest.builder()
                .recipient("test@killme.com")
                .subject("Brat")
                .body("I can see my baby swingin")
                .build();

        emailService.sendRegisterEmail(e);

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        Mockito.verify(mailSender).send(captor.capture());

        SimpleMailMessage mimeMessage1 = captor.getValue();
        //MimeMessageHelper helper  = new MimeMessageHelper(mimeMessage1,true);

        assertEquals("test@killme.com",mimeMessage1.getTo()[0]);
        assertEquals("Brat",mimeMessage1.getSubject());
        assertEquals("I can see my baby swingin",mimeMessage1.getText());
    }

}
