package com.controller;

import com.housingservice.controller.HouseController;
import com.housingservice.dto.HouseDTO;
import com.housingservice.model.House;
import com.housingservice.service.CompositeService;
import com.housingservice.service.HouseService;
import com.housingservice.util.HousingMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HouseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private HouseService houseService;

    @Mock
    private CompositeService compositeService;

    @Mock
    private HousingMapper housingMapper;

    @InjectMocks
    private HouseController houseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(houseController).build();
    }

    @Test
    void testGetHouseDetails() throws Exception {
        Map<String, Object> response = new HashMap<>();
        response.put("houseId", 1);
        response.put("employeeName", "John Doe");

        when(compositeService.getHouseWithEmployeeData(anyInt())).thenReturn(response);

        mockMvc.perform(get("/api/houses/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"houseId\":1,\"employeeName\":\"John Doe\"}"));
    }

    @Test
    void testCreateHouse() throws Exception {
        HouseDTO houseDTO = new HouseDTO();
        houseDTO.setId(1);
        houseDTO.setAddress("House 1");

        House house = new House();
        house.setId(1);
        house.setAddress("House 1");

        when(housingMapper.toHouseEntity(any(HouseDTO.class))).thenReturn(house);
        when(houseService.addHouse(any(House.class))).thenReturn(house);
        when(housingMapper.toHouseDTO(any(House.class))).thenReturn(houseDTO);

        mockMvc.perform(post("/api/houses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"House 1\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"House 1\"}"));
    }

    @Test
    void testGetAllHouses() throws Exception {
        Map<String, Object> response = new HashMap<>();
        response.put("houseId", 1);
        response.put("employeeName", "John Doe");

        when(compositeService.getAllHousesWithEmployeeData()).thenReturn(Collections.singletonList(response));

        mockMvc.perform(get("/api/houses"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"houseId\":1,\"employeeName\":\"John Doe\"}]"));
    }
}