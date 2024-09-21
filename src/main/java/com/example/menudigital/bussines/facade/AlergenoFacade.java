package com.example.menudigital.bussines.facade;

import com.example.menudigital.bussines.facade.Base.BaseFacade;
import com.example.menudigital.domain.dtos.alergeno.AlergenoDto;
import org.springframework.http.ResponseEntity;

public interface AlergenoFacade extends BaseFacade<AlergenoDto, Long> {
    // Método para eliminar una imagen por su identificador público y UUID
    ResponseEntity<String> deleteImage(String publicId, Long id);
}
