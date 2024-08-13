package com.example.menudigital.bussines.services.Impl;

import com.example.menudigital.bussines.services.ImageService;
import com.example.menudigital.bussines.services.ImagenAlergenoService;
import com.example.menudigital.bussines.services.base.BaseServiceImp;
import com.example.menudigital.domain.entities.ImagenAlergeno;
import com.example.menudigital.repositories.ImagenAlergenoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImagenAlergenoServiceImpl extends BaseServiceImp<ImagenAlergeno,Long> implements ImagenAlergenoService {

    @Autowired
    private ImagenAlergenoRepository imagenAlergenoRepository;
    @Autowired
    private ImageService imageService;

    @Override
    @Transactional
    public void deleteById(Long id) {
        var imagenArticulo = imagenAlergenoRepository.getById(id);
        Path filePath = Paths.get(imagenArticulo.getUrl());
        imageService.deleteImage(filePath);
        imagenAlergenoRepository.delete(imagenArticulo);

    }


}
