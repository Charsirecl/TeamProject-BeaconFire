package com.example.applicationservice.controller;

import com.example.applicationservice.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String title = String.format("%s_%d_%s", "OPTReceipt", 1, file.getOriginalFilename());
        String fileUrl = fileService.uploadFile(file, title);
        return new ResponseEntity<>(fileUrl, HttpStatus.OK);
    }
}

