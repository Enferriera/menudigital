package com.example.menudigital.presentation.rest;

import com.example.menudigital.bussines.facade.Impl.EmpresaFacadeImpl;
import com.example.menudigital.bussines.services.ImagenArticuloService;
import com.example.menudigital.domain.dtos.ImagenDto;
import com.example.menudigital.domain.dtos.empresaDto.EmpresaDto;
import com.example.menudigital.domain.entities.Empresa;
import com.example.menudigital.domain.entities.ImagenArticulo;
import com.example.menudigital.presentation.rest.Base.BaseControllerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/imagenArticulos")
@CrossOrigin(origins = {"https://dashboard-menu-project.vercel.app", "http://localhost:5173"})

public class ImagenArticuloController {
    @Autowired
    public ImagenArticuloService imagenArticuloService;

    @DeleteMapping("/{idImage}")
    public void deleteImage(@PathVariable Long idImage){
        imagenArticuloService.deleteById(idImage);
    }
}
