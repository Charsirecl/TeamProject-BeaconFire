package com.service;

import com.housingservice.client.EmployeeClient;
import com.housingservice.exception.ResourceNotFoundException;
import com.housingservice.model.House;
import com.housingservice.repository.HouseRepository;
import com.housingservice.service.CompositeService;
import com.housingservice.service.FacilityReportDetailService;
import com.housingservice.service.FacilityReportService;
import com.housingservice.service.HouseService;
import feign.FeignException;
import feign.Request;
import feign.RequestTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class CompositeServiceTest {

    @InjectMocks
    private CompositeService compositeService;

    @Mock
    private FacilityReportService facilityReportService;

    @Mock
    private FacilityReportDetailService facilityReportDetailService;

    @Mock
    private HouseService houseService;

    @Mock
    private EmployeeClient employeeClient;

    @Mock
    private HouseRepository houseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEmployeeNameById_Success() {
        Map<String, Object> employeeData = new HashMap<>();
        employeeData.put("firstName", "John");
        employeeData.put("lastName", "Doe");

        when(employeeClient.getEmployeeById("123")).thenReturn(employeeData);

        String result = compositeService.getEmployeeNameById("123");
        assertEquals("John Doe", result);
    }

    @Test
    void testGetEmployeeNameById_NotFound() {
        Request request = Request.create(Request.HttpMethod.GET, "/employee/123", Collections.emptyMap(), null, new RequestTemplate());

        when(employeeClient.getEmployeeById("123"))
                .thenThrow(new FeignException.NotFound("Employee not found", request, (byte[]) null, (Map<String, Collection<String>>) StandardCharsets.UTF_8));

        String result = compositeService.getEmployeeNameById("123");
        assertEquals("Employee not found", result);
    }
    @Test
    void testGetHouseWithEmployeeData_Success() {
        House house = new House();
        house.setId(1);
        house.setAddress("123 Main St");

        List<Map<String, Object>> employeeDetails = Collections.singletonList(new HashMap<>());

        when(houseService.getHouseById(1)).thenReturn(Optional.of(house));
        when(employeeClient.getEmployeesByHouseId(1)).thenReturn(employeeDetails);

        Map<String, Object> result = compositeService.getHouseWithEmployeeData(1);

        assertEquals(house, result.get("house"));
        assertEquals(employeeDetails, result.get("employees"));
    }

    @Test
    void testGetHouseWithEmployeeData_NotFound() {
        when(houseService.getHouseById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> compositeService.getHouseWithEmployeeData(1));
    }

    @Test
    void testGetAllHousesWithEmployeeData() {
        House house = new House();
        house.setId(1);
        house.setAddress("123 Main St");

        List<House> houses = Collections.singletonList(house);
        List<Map<String, Object>> employeeDetails = Collections.singletonList(new HashMap<>());

        when(houseRepository.findAll()).thenReturn(houses);
        when(employeeClient.getEmployeesByHouseId(1)).thenReturn(employeeDetails);

        List<Map<String, Object>> result = compositeService.getAllHousesWithEmployeeData();

        assertEquals(1, result.size());
        assertEquals("123 Main St", result.get(0).get("address"));
        assertEquals(employeeDetails, result.get(0).get("employees"));
    }
}