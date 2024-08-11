package com.example.menudigital.presentation.rest;

import com.example.menudigital.bussines.services.ImageService;
import com.example.menudigital.domain.entities.ImagenBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/images")
@CrossOrigin(origins = {"https://dashboard-menu-project.vercel.app", "http://localhost:5173"})

public class ImageController {

    @Value("${image.folder.path}")
    private String imageFolderPath;

    @Autowired
    private ImageService imageService;

    private static final Map<String, MediaType> mediaTypeMap = new HashMap<>();

    static {
        mediaTypeMap.put("jpg", MediaType.IMAGE_JPEG);
        mediaTypeMap.put("jpeg", MediaType.IMAGE_JPEG);
        mediaTypeMap.put("png", MediaType.IMAGE_PNG);
        mediaTypeMap.put("gif", MediaType.IMAGE_GIF);
        mediaTypeMap.put("obj", MediaType.APPLICATION_OCTET_STREAM); // Ajusta según el tipo de contenido para .obj
        mediaTypeMap.put("fbx", MediaType.APPLICATION_OCTET_STREAM); // Ajusta según el tipo de contenido para .fbx
        mediaTypeMap.put("stl", MediaType.APPLICATION_OCTET_STREAM); // Ajusta según el tipo de contenido para .stl
    }

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


   @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(imageFolderPath, filename);
            Resource file = new FileSystemResource(filePath);
            if (file.exists() && file.isReadable()) {
                String fileExtension = getFileExtension(filename);
                MediaType mediaType = mediaTypeMap.getOrDefault(fileExtension, MediaType.APPLICATION_OCTET_STREAM);

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
                        .contentType(mediaType)
                        .body(file);
            } else {
                throw new IOException("File not found or not readable: " + filename);
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    private String getFileExtension(String filename) {
        if (filename == null) {
            return "";
        }
        int dotIndex = filename.lastIndexOf('.');
        return (dotIndex == -1) ? "" : filename.substring(dotIndex + 1).toLowerCase();
    }
}

