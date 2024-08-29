package com.az.authenticationservice.domain.emailService;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailRequest {

    private String recipient;
    private String subject;
    private String body;

}
