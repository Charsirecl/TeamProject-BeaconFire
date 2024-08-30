package com.controller;

import com.housingservice.controller.FacilityReportController;
import com.housingservice.dto.FacilityReportDTO;
import com.housingservice.dto.FacilityReportDetailDTO;
import com.housingservice.model.FacilityReport;
import com.housingservice.model.FacilityReportDetail;
import com.housingservice.service.FacilityReportDetailService;
import com.housingservice.service.FacilityReportService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FacilityReportControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FacilityReportService facilityReportService;

    @Mock
    private FacilityReportDetailService facilityReportDetailService;

    @Mock
    private HousingMapper housingMapper;

    @InjectMocks
    private FacilityReportController facilityReportController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(facilityReportController).build();
    }

    @Test
    void testGetAllFacilityReports() throws Exception {
        FacilityReportDTO reportDTO = new FacilityReportDTO();
        reportDTO.setId(1);

        when(facilityReportService.getAllFacilityReports()).thenReturn(Collections.singletonList(new FacilityReport()));
        when(housingMapper.toFacilityReportDTO(any(FacilityReport.class))).thenReturn(reportDTO);

        mockMvc.perform(get("/api/facility-reports"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1}]"));
    }

    @Test
    void testGetFacilityReportById() throws Exception {
        FacilityReportDTO reportDTO = new FacilityReportDTO();
        reportDTO.setId(1);

        when(facilityReportService.getFacilityReportById(anyInt())).thenReturn(java.util.Optional.of(new FacilityReport()));
        when(housingMapper.toFacilityReportDTO(any(FacilityReport.class))).thenReturn(reportDTO);

        mockMvc.perform(get("/api/facility-reports/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1}"));
    }

    @Test
    void testGetFacilityReportDetails() throws Exception {
        FacilityReportDetailDTO detailDTO = new FacilityReportDetailDTO();
        detailDTO.setId(1);

        when(facilityReportDetailService.getFacilityReportDetailsByFacilityReportId(anyInt()))
                .thenReturn(Collections.singletonList(new FacilityReportDetail()));
        when(housingMapper.toFacilityReportDetailDTO(any(FacilityReportDetail.class))).thenReturn(detailDTO);

        mockMvc.perform(get("/api/facility-reports/1/details"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1}]"));
    }

    @Test
    void testAddFacilityReportDetail() throws Exception {
        FacilityReportDetailDTO detailDTO = new FacilityReportDetailDTO();
        detailDTO.setId(1);

        FacilityReportDetail detail = new FacilityReportDetail();
        detail.setId(1);

        when(facilityReportService.getFacilityReportById(anyInt())).thenReturn(java.util.Optional.of(new FacilityReport()));
        when(housingMapper.toFacilityReportDetailEntity(any(FacilityReportDetailDTO.class))).thenReturn(detail);
        when(facilityReportDetailService.addFacilityReportDetail(any(FacilityReportDetail.class))).thenReturn(detail);
        when(housingMapper.toFacilityReportDetailDTO(any(FacilityReportDetail.class))).thenReturn(detailDTO);

        mockMvc.perform(post("/api/facility-reports/1/details")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"comment\":\"Test comment\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1}"));
    }

    @Test
    void testUpdateFacilityReportDetail() throws Exception {
        FacilityReportDetailDTO detailDTO = new FacilityReportDetailDTO();
        detailDTO.setComment("Updated comment");

        FacilityReportDetail detail = new FacilityReportDetail();
        detail.setId(1);

        when(facilityReportDetailService.getFacilityReportDetailById(anyInt())).thenReturn(java.util.Optional.of(detail));
        when(facilityReportDetailService.updateFacilityReportDetail(any(FacilityReportDetail.class)))
                .thenReturn(detail);
        when(housingMapper.toFacilityReportDetailDTO(any(FacilityReportDetail.class))).thenReturn(detailDTO);

        mockMvc.perform(put("/api/facility-reports/details/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"comment\":\"Updated comment\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"comment\":\"Updated comment\"}"));
    }

    @Test
    void testDeleteFacilityReportDetail() throws Exception {
        mockMvc.perform(delete("/api/facility-reports/details/1"))
                .andExpect(status().isNoContent());
    }
}