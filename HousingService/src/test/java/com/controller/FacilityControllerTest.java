package com.controller;

import com.housingservice.dto.FacilityDTO;
import com.housingservice.model.Facility;
import com.housingservice.service.FacilityService;
import com.housingservice.util.HousingMapper;
import com.housingservice.controller.FacilityController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FacilityControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FacilityService facilityService;

    @Mock
    private HousingMapper housingMapper;

    @InjectMocks
    private FacilityController facilityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(facilityController).build();
    }

    @Test
    void testGetAllFacilities() throws Exception {
        FacilityDTO facilityDTO = new FacilityDTO();
        facilityDTO.setId(1);
        facilityDTO.setType("Gym");

        when(facilityService.getAllFacilities()).thenReturn(Collections.singletonList(new Facility()));
        when(housingMapper.toFacilityDTO(any(Facility.class))).thenReturn(facilityDTO);

        mockMvc.perform(get("/api/facilities"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Gym\"}]"));
    }

    @Test
    void testGetFacilityById() throws Exception {
        FacilityDTO facilityDTO = new FacilityDTO();
        facilityDTO.setId(1);
        facilityDTO.setType("Gym");

        when(facilityService.getFacilityById(anyInt())).thenReturn(java.util.Optional.of(new Facility()));
        when(housingMapper.toFacilityDTO(any(Facility.class))).thenReturn(facilityDTO);

        mockMvc.perform(get("/api/facilities/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Gym\"}"));
    }

    @Test
    void testCreateFacility() throws Exception {
        FacilityDTO facilityDTO = new FacilityDTO();
        facilityDTO.setId(1);
        facilityDTO.setType("Gym");

        Facility facility = new Facility();
        facility.setId(1);
        facility.setType("Gym");

        when(housingMapper.toFacilityEntity(any(FacilityDTO.class))).thenReturn(facility);
        when(facilityService.addFacility(any(Facility.class))).thenReturn(facility);
        when(housingMapper.toFacilityDTO(any(Facility.class))).thenReturn(facilityDTO);

        mockMvc.perform(post("/api/facilities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Gym\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Gym\"}"));
    }

    @Test
    void testUpdateFacility() throws Exception {
        FacilityDTO facilityDTO = new FacilityDTO();
        facilityDTO.setId(1);
        facilityDTO.setType("Gym");

        Facility facility = new Facility();
        facility.setId(1);
        facility.setType("Gym");

        when(housingMapper.toFacilityEntity(any(FacilityDTO.class))).thenReturn(facility);
        when(facilityService.updateFacility(any(Facility.class))).thenReturn(facility);
        when(housingMapper.toFacilityDTO(any(Facility.class))).thenReturn(facilityDTO);

        mockMvc.perform(put("/api/facilities/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Gym\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Gym\"}"));
    }

    @Test
    void testDeleteFacility() throws Exception {
        mockMvc.perform(delete("/api/facilities/1"))
                .andExpect(status().isNoContent());
    }
}
