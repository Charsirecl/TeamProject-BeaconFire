package com.composite.visastatuscompositeservice.service;

import com.composite.visastatuscompositeservice.client.ApplicationWorkFlowServiceClient;
import com.composite.visastatuscompositeservice.client.DigitalDocumentServiceClient;
import com.composite.visastatuscompositeservice.client.EmployeeServiceClient;
import com.composite.visastatuscompositeservice.dto.ApplicationService.ApplicationWorkFlow;
import com.composite.visastatuscompositeservice.dto.ApplicationService.DigitalDocument;
import com.composite.visastatuscompositeservice.dto.EmployeeService.PersonalDocument;
import com.composite.visastatuscompositeservice.dto.EmployeeService.VisaStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class VisaStatusCompositeService {

    @Autowired
    private ApplicationWorkFlowServiceClient applicationWorkFlowServiceClient;

    @Autowired
    private DigitalDocumentServiceClient digitalDocumentServiceClient;

    @Autowired
    private EmployeeServiceClient employeeServiceClient;

    public void updateEmployeeDocument(int employeeId, MultipartFile file, String documentType, String description, boolean isRequired) {
        // Step 1: Check and add a new ApplicationWorkFlow record
        ApplicationWorkFlow applicationWorkFlow = new ApplicationWorkFlow();
        applicationWorkFlow.setEmployeeID(employeeId);
        applicationWorkFlow.setStatus("Pending");

        String token = "Bearer:eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huX2RvZSIsInBlcm1pc3Npb25zIjpbeyJhdXRob3JpdHkiOiJIUiJ9XX0.eF-ZFCO_tsWBsFMdDBef8MTT3-AONRDR7-n1qRYVSGE";

        ResponseEntity<List<ApplicationWorkFlow>> existingApplicationsResponse = applicationWorkFlowServiceClient.getApplicationsByEmployeeId(employeeId, token);
        List<ApplicationWorkFlow> existingApplications = existingApplicationsResponse.getBody();

        if (existingApplications != null) {
            for (ApplicationWorkFlow app : existingApplications) {
                if (app.getStatus().equals("Pending")) {
                    throw new IllegalStateException("Cannot submit a new application while another is pending.");
                }
            }
        }

//        applicationWorkFlowServiceClient.submitApplication(applicationWorkFlow);

        applicationWorkFlowServiceClient.submitApplication(
                applicationWorkFlow.getEmployeeID(),
                applicationWorkFlow.getStatus(),
                applicationWorkFlow.getComment(),
                token);


        // Step 2: Upload the new file to AWS S3 and create a new record in the DigitalDocument table
        String title = String.format("%s_%s_%s", documentType.replaceAll(" ", ""), employeeId, file.getOriginalFilename());
//        ResponseEntity<DigitalDocument> documentResponse =
//                digitalDocumentServiceClient.uploadDocument(file, documentType, isRequired,
//                                                            description, String.valueOf(employeeId));
//        DigitalDocument savedDocument = documentResponse.getBody();
//
//        // Step 3: Update the visa status and personal document in Employee service
        VisaStatus visaStatus = new VisaStatus();
        visaStatus.setVisaType(documentType);
        visaStatus.setActiveFlag(true);
        visaStatus.setStartDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        visaStatus.setLastModificationDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        PersonalDocument personalDocument = new PersonalDocument();
//        personalDocument.setPath(savedDocument.getPath());
        personalDocument.setTitle(title);
        personalDocument.setComment(description);
        personalDocument.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        employeeServiceClient.updateVisaStatus(String.valueOf(employeeId), visaStatus);
        employeeServiceClient.updatePersonalDocument(String.valueOf(employeeId), personalDocument);
    }
}

