package com.example.menudigital.bussines.services.Impl;

import com.example.menudigital.bussines.services.ImageService;
import com.example.menudigital.bussines.services.ImagenArticuloService;
import com.example.menudigital.bussines.services.base.BaseServiceImp;
import com.example.menudigital.domain.entities.ImagenArticulo;
import com.example.menudigital.repositories.ImagenArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImagenArticuloServiceImpl extends BaseServiceImp<ImagenArticulo, Long> implements ImagenArticuloService {

    @Autowired
    private ImagenArticuloRepository imagenArticuloRepository;
    @Autowired
    private ImageService imageService;

    @Override
    public void deleteById(Long id) {
        var imagenArticulo = imagenArticuloRepository.getById(id);
        Path filePath = Paths.get(imagenArticulo.getUrl());
        imageService.deleteImage(filePath);
        imagenArticuloRepository.delete(imagenArticulo);

    }

}
