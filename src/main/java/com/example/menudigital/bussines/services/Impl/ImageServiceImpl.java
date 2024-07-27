package com.example.menudigital.bussines.services.Impl;

import com.example.menudigital.bussines.services.ImageService;
import com.example.menudigital.domain.entities.ImagenBase;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    private static final String UPLOAD_DIR = "src/main/resources/images/";
    @Override
    public ImagenBase save(MultipartFile file) {
        LocalDateTime now = LocalDateTime.now();
        // Formatea la fecha y hora como una cadena
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = now.format(formatter);
        // Agrega la fecha y hora al nombre del archivo
        String fileName =timestamp + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);

        try {
            Files.createDirectories(filePath.getParent());
            file.transferTo(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Error saving file", e);
        }

        ImagenBase imagenBase = new ImagenBase();
        imagenBase.setName(fileName);
        imagenBase.setUrl(filePath.toString());

        return imagenBase;

    }

    @Override
    public void deleteImage(Path filePath) {
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Error deleting file", e);
        }
    }

    @Override
    public List<ImagenBase> saveAll(MultipartFile[] files) {
        List<ImagenBase> savedImages = new ArrayList<>();
        for (MultipartFile file : files) {
            ImagenBase savedImage = save(file);
            savedImages.add(savedImage);
        }
        return savedImages;

    }
}
