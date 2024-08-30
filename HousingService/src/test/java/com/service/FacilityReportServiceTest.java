package com.service;

import com.housingservice.model.FacilityReport;
import com.housingservice.model.Facility;
import com.housingservice.repository.FacilityReportRepository;
import com.housingservice.repository.FacilityRepository;
import com.housingservice.service.FacilityReportService;
import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class FacilityReportServiceTest {

    @InjectMocks
    private FacilityReportService facilityReportService;

    @Mock
    private FacilityReportRepository facilityReportRepository;

    @Mock
    private FacilityRepository facilityRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFacilityReports() {
        FacilityReport report = new FacilityReport();
        when(facilityReportRepository.findAll()).thenReturn(Collections.singletonList(report));

        var result = facilityReportService.getAllFacilityReports();
        assertEquals(1, result.size());
        assertEquals(report, result.get(0));
    }

    @Test
    void testGetFacilityReportById() {
        FacilityReport report = new FacilityReport();
        when(facilityReportRepository.findById(anyInt())).thenReturn(Optional.of(report));

        var result = facilityReportService.getFacilityReportById(1);
        assertTrue(result.isPresent());
        assertEquals(report, result.get());
    }

    @Test
    void testAddFacilityReport_ValidFacility() {
        Facility facility = new Facility();
        facility.setId(1);
        FacilityReport report = new FacilityReport();
        report.setFacility(facility);

        when(facilityRepository.findById(anyInt())).thenReturn(Optional.of(facility));
        when(facilityReportRepository.save(report)).thenReturn(report);

        var result = facilityReportService.addFacilityReport(report);
        assertEquals(report, result);
    }

    @Test
    void testAddFacilityReport_InvalidFacility() {
        FacilityReport report = new FacilityReport();
        when(facilityRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> facilityReportService.addFacilityReport(report));
    }

    @Test
    void testUpdateFacilityReport_ValidFacility() {
        Facility facility = new Facility();
        facility.setId(1);
        FacilityReport report = new FacilityReport();
        report.setFacility(facility);

        when(facilityRepository.findById(anyInt())).thenReturn(Optional.of(facility));
        when(facilityReportRepository.save(report)).thenReturn(report);

        var result = facilityReportService.updateFacilityReport(report);
        assertEquals(report, result);
    }

    @Test
    void testUpdateFacilityReport_InvalidFacility() {
        FacilityReport report = new FacilityReport();
        when(facilityRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> facilityReportService.updateFacilityReport(report));
    }

    @Test
    void testDeleteFacilityReport() {
        doNothing().when(facilityReportRepository).deleteById(anyInt());

        facilityReportService.deleteFacilityReport(1);
        verify(facilityReportRepository, times(1)).deleteById(1);
    }
}