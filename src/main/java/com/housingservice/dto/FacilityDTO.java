package com.housingservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class FacilityDTO {
    private Integer id;
    private Integer houseId; // To represent the relationship with House entity
    private String type;
    private Integer quantity;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime lastModificationDate;
}
