package com.example.menudigital.bussines.services.Impl;

import com.example.menudigital.bussines.services.AlergenoService;
import com.example.menudigital.bussines.services.ArticuloService;
import com.example.menudigital.bussines.services.CloudinaryService;
import com.example.menudigital.bussines.services.base.BaseServiceImp;
import com.example.menudigital.domain.entities.Alergeno;
import com.example.menudigital.repositories.AlergenoRepository;
import com.example.menudigital.repositories.ImagenAlergenoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AlergenoServiceImpl extends BaseServiceImp<Alergeno,Long> implements AlergenoService {

    @Autowired
    private ArticuloService articuloService;
    @Autowired
    private ImagenAlergenoRepository imagenAlergenoRepository;
    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public void deleteById(Long id) {
        if (articuloService.existsArticuloByAlergeno(id)) {
            throw new RuntimeException("No se puede eliminar el alergeno porque tiene articulos asociados");
        }
        super.deleteById(id);
    }

    // Método para eliminar una imagen por su identificador en la base de datos y en Cloudinary
    @Override
    public ResponseEntity<String> deleteImage(String publicId, Long id) {
        try {
            // Eliminar la imagen de la base de datos usando su identificador
            imagenAlergenoRepository.deleteById(id);

            // Llamar al servicio de Cloudinary para eliminar la imagen por su publicId
            return cloudinaryService.deleteImage(publicId);

        } catch (Exception e) {
            e.printStackTrace();
            // Devolver un error (400) si ocurre alguna excepción durante la eliminación
            return new ResponseEntity<>("{\"status\":\"ERROR\", \"message\":\"" + e.getMessage() + "\"}", HttpStatus.BAD_REQUEST);
        }
    }
}
