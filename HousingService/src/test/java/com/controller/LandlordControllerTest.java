package com.controller;

import com.housingservice.controller.LandlordController;
import com.housingservice.dto.LandlordDTO;
import com.housingservice.model.Landlord;
import com.housingservice.service.LandlordService;
import com.housingservice.util.HousingMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LandlordControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LandlordService landlordService;

    @Mock
    private HousingMapper housingMapper;

    @InjectMocks
    private LandlordController landlordController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(landlordController).build();
    }

    @Test
    void testCreateLandlord() throws Exception {
        LandlordDTO landlordDTO = new LandlordDTO();
        landlordDTO.setId(1);
        landlordDTO.setFirstName("John");
        landlordDTO.setLastName("Doe");
        landlordDTO.setEmail("john.doe@example.com");
        landlordDTO.setCellPhone("123-456-7890");

        Landlord landlord = new Landlord();
        landlord.setId(1);
        landlord.setFirstName("John");
        landlord.setLastName("Doe");
        landlord.setEmail("john.doe@example.com");
        landlord.setCellPhone("123-456-7890");

        when(housingMapper.toLandlordEntity(any(LandlordDTO.class))).thenReturn(landlord);
        when(landlordService.addLandlord(any(Landlord.class))).thenReturn(landlord);
        when(housingMapper.toLandlordDTO(any(Landlord.class))).thenReturn(landlordDTO);

        mockMvc.perform(post("/api/landlords")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"cellPhone\":\"123-456-7890\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"cellPhone\":\"123-456-7890\"}"));
    }
}