package com.composite.visastatuscompositeservice.client;

//import com.composite.visastatuscompositeservice.config.FeignConfig;
import com.composite.visastatuscompositeservice.config.FeignConfig;
import com.composite.visastatuscompositeservice.dto.ApplicationService.DigitalDocument;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "digital-document-service", url = "http://localhost:8081", configuration = FeignConfig.class)
public interface DigitalDocumentServiceClient {

    @PostMapping(value = "/api/documents/upload", consumes = "multipart/form-data")
    ResponseEntity<DigitalDocument> uploadDocument(@RequestPart("file") MultipartFile file,
                                                   @RequestPart("type") String type,
                                                   @RequestPart("isRequired") boolean isRequired,
                                                   @RequestPart("description") String description,
                                                   @RequestPart("employeeId") String employeeId);
}

