package com.housingservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LandlordDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String cellPhone;
    private LocalDateTime createDate;
    private LocalDateTime lastModificationDate;
}
