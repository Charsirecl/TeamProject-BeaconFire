package com.example.applicationservice.controller;

import com.example.applicationservice.domain.DigitalDocument;
import com.example.applicationservice.service.DigitalDocumentService;
import com.example.applicationservice.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DigitalDocumentController {

    @Autowired
    private DigitalDocumentService digitalDocumentService;

    @Autowired
    private FileService fileService; // Service to handle S3 file uploads

    @PostMapping("/upload")
    public ResponseEntity<DigitalDocument> uploadDocument(@RequestParam("file") MultipartFile file,
                                                          @RequestParam("type") String type,
                                                          @RequestParam("isRequired") boolean isRequired,
                                                          @RequestParam("description") String description,
//                                                          @RequestParam("title") String title,
                                                          @RequestParam("employeeId") String employeeId) {  // Add employeeId here

        String title = String.format("%s_%s_%s", type.replaceAll(" ", ""), employeeId, file.getOriginalFilename());

        String path = fileService.uploadFile(file, title); // Upload the file to S3 and get the file URL

        DigitalDocument document = new DigitalDocument();
        document.setType(type);
        document.setIsRequired(isRequired);
        document.setPath(path);
        document.setDescription(description);
        document.setTitle(title);
        document.setEmployeeId(employeeId);  // Set employeeId

        DigitalDocument savedDocument = digitalDocumentService.saveDocument(document);
        return ResponseEntity.ok(savedDocument);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DigitalDocument>> getAllDocuments() {
        List<DigitalDocument> documents = digitalDocumentService.getAllDocuments();
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<DigitalDocument>> getDocumentsByEmployeeId(@PathVariable String employeeId) {
        List<DigitalDocument> documents = digitalDocumentService.getDocumentsByEmployeeId(employeeId);
        return ResponseEntity.ok(documents);
    }
}
