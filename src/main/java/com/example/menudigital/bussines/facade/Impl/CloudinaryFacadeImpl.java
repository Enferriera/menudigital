package com.example.menudigital.bussines.facade.Impl;


import com.example.menudigital.bussines.facade.CloudinaryFacade;
import com.example.menudigital.bussines.services.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CloudinaryFacadeImpl implements CloudinaryFacade {
    @Autowired
    private CloudinaryService cloudinaryService;


    @Override
    public String uploadFile(MultipartFile file) {
        return cloudinaryService.uploadFile(file);
    }

    @Override
    public ResponseEntity<String> deleteImage(String publicId) {
        return cloudinaryService.deleteImage(publicId);
    }
}
