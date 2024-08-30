package com.service;

import com.housingservice.model.FacilityReportDetail;
import com.housingservice.repository.FacilityReportDetailRepository;
import com.housingservice.service.FacilityReportDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class FacilityReportDetailServiceTest {

    @InjectMocks
    private FacilityReportDetailService facilityReportDetailService;

    @Mock
    private FacilityReportDetailRepository facilityReportDetailRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFacilityReportDetails() {
        FacilityReportDetail detail = new FacilityReportDetail();
        when(facilityReportDetailRepository.findAll()).thenReturn(Collections.singletonList(detail));

        List<FacilityReportDetail> result = facilityReportDetailService.getAllFacilityReportDetails();
        assertEquals(1, result.size());
        assertEquals(detail, result.get(0));
    }

    @Test
    void testGetFacilityReportDetailById() {
        FacilityReportDetail detail = new FacilityReportDetail();
        when(facilityReportDetailRepository.findById(anyInt())).thenReturn(Optional.of(detail));

        Optional<FacilityReportDetail> result = facilityReportDetailService.getFacilityReportDetailById(1);
        assertTrue(result.isPresent());
        assertEquals(detail, result.get());
    }

    @Test
    void testAddFacilityReportDetail() {
        FacilityReportDetail detail = new FacilityReportDetail();
        when(facilityReportDetailRepository.save(detail)).thenReturn(detail);

        FacilityReportDetail result = facilityReportDetailService.addFacilityReportDetail(detail);
        assertEquals(detail, result);
    }

    @Test
    void testUpdateFacilityReportDetail() {
        FacilityReportDetail detail = new FacilityReportDetail();
        when(facilityReportDetailRepository.save(detail)).thenReturn(detail);

        FacilityReportDetail result = facilityReportDetailService.updateFacilityReportDetail(detail);
        assertEquals(detail, result);
    }

    @Test
    void testDeleteFacilityReportDetail() {
        doNothing().when(facilityReportDetailRepository).deleteById(anyInt());

        facilityReportDetailService.deleteFacilityReportDetail(1);
        verify(facilityReportDetailRepository, times(1)).deleteById(1);
    }

    @Test
    void testGetFacilityReportDetailsByFacilityReportId() {
        FacilityReportDetail detail = new FacilityReportDetail();
        when(facilityReportDetailRepository.findByFacilityReportId(anyInt())).thenReturn(Collections.singletonList(detail));

        List<FacilityReportDetail> result = facilityReportDetailService.getFacilityReportDetailsByFacilityReportId(1);
        assertEquals(1, result.size());
        assertEquals(detail, result.get(0));
    }
}