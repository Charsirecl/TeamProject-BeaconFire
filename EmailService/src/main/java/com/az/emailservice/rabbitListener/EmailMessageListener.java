package com.az.emailservice.rabbitListener;

import com.az.emailservice.enetity.EmailRequest;
import com.az.emailservice.service.EmailService;
import com.az.emailservice.util.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


@Component
public class EmailMessageListener {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "rejectNotice")
    public void onMessage(String message) {
        EmailRequest emailRequest = SerializeUtil.deserialize(message, EmailRequest.class);
        emailService.sendRegisterEmail(emailRequest);
    }
}

