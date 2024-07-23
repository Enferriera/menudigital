package com.example.menudigital.bussines.services;

import com.example.menudigital.domain.entities.ImagenArticulo;
import com.example.menudigital.domain.entities.ImagenBase;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;


public interface ImageService  {
    public ImagenBase save(MultipartFile file);
    public void deleteImage(Path filePath);
    public List<ImagenBase> saveAll(MultipartFile[] files);

}
