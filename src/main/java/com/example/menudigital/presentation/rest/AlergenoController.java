package com.example.menudigital.presentation.rest;

import com.example.menudigital.bussines.facade.Impl.AlergenoFacadeImpl;
import com.example.menudigital.domain.dtos.alergeno.AlergenoDto;
import com.example.menudigital.domain.entities.Alergeno;
import com.example.menudigital.presentation.rest.Base.BaseControllerImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alergenos")
@CrossOrigin(origins = {"https://dashboard-menu-project.vercel.app", "http://localhost:5173"})

public class AlergenoController extends BaseControllerImp<Alergeno, AlergenoDto, Long, AlergenoFacadeImpl> {
    public AlergenoController(AlergenoFacadeImpl facade) {
        super(facade);
    }

    @PostMapping("/deleteImg")
    public ResponseEntity<String> deleteById(
            @RequestParam(value = "publicId") String publicId,
            @RequestParam(value = "id") Long id) {
        try {
            return facade.deleteImage(publicId, id); // Llama al método del servicio para eliminar la imagen
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Invalid UUID format"); // Respuesta HTTP 400 si el UUID no es válido
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred"); // Respuesta HTTP 500 si ocurre un error inesperado
        }
    }
}
