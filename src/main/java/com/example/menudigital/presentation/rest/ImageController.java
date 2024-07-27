package com.example.menudigital.presentation.rest;

import com.example.menudigital.bussines.services.ImageService;
import com.example.menudigital.domain.entities.ImagenBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/images")
@CrossOrigin(origins="*")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ImagenBase> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("Please select a file to upload.");
        }

        return ResponseEntity.ok(imageService.save(file));

    }

    @PostMapping("/uploadAll")
    public ResponseEntity<List<ImagenBase>> handleFileUploadAll(@RequestParam("files") MultipartFile[] files) {
        if (files.length == 0) {
            throw new RuntimeException("Please select a file to upload.");
        }

        return ResponseEntity.ok(imageService.saveAll(files));
    }
}
