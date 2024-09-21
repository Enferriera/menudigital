package com.example.menudigital.bussines.services;

import com.example.menudigital.bussines.services.base.BaseService;
import com.example.menudigital.domain.entities.Alergeno;
import org.springframework.http.ResponseEntity;

public interface AlergenoService extends BaseService<Alergeno, Long> {
    // Método para eliminar una imagen por su identificador público y Long
    ResponseEntity<String> deleteImage(String publicId, Long id);
}
