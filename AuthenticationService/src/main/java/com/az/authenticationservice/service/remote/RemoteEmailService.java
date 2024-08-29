package com.az.authenticationservice.service.remote;

import com.az.authenticationservice.domain.emailService.EmailRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("email-service")
public interface RemoteEmailService {

    @PostMapping("email-service/mail/send")
//    @RequestMapping(method = RequestMethod.POST,value = "/email-service/mail/send")
    ResponseEntity<EmailRequest> sendEmail(@RequestBody EmailRequest emailRequest, @RequestHeader(value = "Authorization")String authorization);
}
