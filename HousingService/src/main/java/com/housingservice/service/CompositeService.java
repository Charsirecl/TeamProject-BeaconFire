package com.housingservice.service;

import com.housingservice.client.EmployeeClient;
import com.housingservice.dto.FacilityReportDTO;
import com.housingservice.dto.FacilityReportDetailDTO;
import com.housingservice.exception.ResourceNotFoundException;
import com.housingservice.model.FacilityReport;
import com.housingservice.model.House;
import com.housingservice.repository.HouseRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompositeService {
    @Autowired
    private FacilityReportService facilityReportService;

    @Autowired
    private FacilityReportDetailService facilityReportDetailService;

    @Autowired
    private HouseService houseService;

    @Autowired
    private EmployeeClient employeeClient;

    @Autowired
    private HouseRepository houseRepository;

    public String getEmployeeNameById(String employeeId) {
        try {
            Map<String, Object> employeeData = employeeClient.getEmployeeById(employeeId);
            String firstName = (String) employeeData.get("firstName");
            String lastName = (String) employeeData.get("lastName");
            return firstName + " " + lastName;
        } catch (FeignException.NotFound e) {
            return "Employee not found";
        } catch (Exception e) {
            return "Error fetching employee name";
        }
    }


    public Map<String, Object> getHouseWithEmployeeData(Integer houseId) {
        Optional<House> houseOpt = houseService.getHouseById(houseId);
        if (!houseOpt.isPresent()) {
            throw new ResourceNotFoundException("House not found");
        }
        House house = houseOpt.get();

        List<Map<String, Object>> employeeDetails = employeeClient.getEmployeesByHouseId(houseId);

        Map<String, Object> response = new HashMap<>();
        response.put("house", house);
        response.put("employees", employeeDetails);

        return response;
    }


    public List<Map<String, Object>> getAllHousesWithEmployeeData() {
        List<House> houses = houseRepository.findAll();
        return houses.stream()
                .map(house -> {
                    List<Map<String, Object>> employeeDetails = employeeClient.getEmployeesByHouseId(house.getId());

                    Map<String, Object> houseData = new HashMap<>();
                    houseData.put("id", house.getId());
                    houseData.put("address", house.getAddress());
                    houseData.put("maxOccupant", house.getMaxOccupant());
                    houseData.put("description", house.getDescription());
                    houseData.put("employees", employeeDetails);
                    List<Map<String, Object>> facilities = house.getFacilities().stream()
                            .map(facility -> {
                                Map<String, Object> facilityData = new HashMap<>();
                                facilityData.put("id", facility.getId());
                                facilityData.put("type", facility.getType());
                                facilityData.put("quantity", facility.getQuantity());
                                facilityData.put("description", facility.getDescription());
                                return facilityData;
                            })
                            .collect(Collectors.toList());

                    houseData.put("facilities", facilities);
                    return houseData;
                })
                .collect(Collectors.toList());
    }

}
