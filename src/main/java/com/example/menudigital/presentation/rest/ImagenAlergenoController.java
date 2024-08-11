package com.example.menudigital.presentation.rest;

import com.example.menudigital.bussines.services.ImagenAlergenoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/imagenAlergenos")
@CrossOrigin(origins = {"https://dashboard-menu-project.vercel.app", "http://localhost:5173"})

public class ImagenAlergenoController {

    @Autowired
    private ImagenAlergenoService imagenAlergenoService;

    @DeleteMapping("/{idImage}")
    public void deleteImage(@PathVariable Long idImage){
        imagenAlergenoService.deleteById(idImage);
    }
}
