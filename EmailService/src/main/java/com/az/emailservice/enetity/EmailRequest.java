package com.az.emailservice.enetity;

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
