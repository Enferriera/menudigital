package com.example.menudigital.presentation.rest;


import com.example.menudigital.bussines.facade.Impl.LocalidadFacadeImp;
import com.example.menudigital.domain.dtos.localidadDto.LocalidadDto;
import com.example.menudigital.domain.entities.Localidad;
import com.example.menudigital.presentation.rest.Base.BaseControllerImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localidades")
@CrossOrigin(origins="*")
public class LocalidadController extends BaseControllerImp<Localidad, LocalidadDto, Long, LocalidadFacadeImp> {

    public LocalidadController(LocalidadFacadeImp facade) {
        super(facade);
    }
    private static final Logger logger = LoggerFactory.getLogger(LocalidadController.class);

    @GetMapping("findByProvincia/{idProvincia}")
    public ResponseEntity<List<LocalidadDto>> getByProvincia(@PathVariable Long idProvincia) {
        logger.info("INICIO GET BY PROVINCIA");
        return ResponseEntity.ok(facade.findByProvinciaId(idProvincia));
    }
}
