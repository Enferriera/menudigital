package com.example.menudigital.presentation.rest;


import com.example.menudigital.bussines.facade.Impl.ProvinciaFacadeImp;
import com.example.menudigital.domain.dtos.provinciaDto.ProvinciaDto;
import com.example.menudigital.domain.entities.Provincia;
import com.example.menudigital.presentation.rest.Base.BaseControllerImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provincias")
@CrossOrigin(origins={" https://dashboard-menu-project.vercel.app/",
        "link: http://localhost:5173"})
public class ProvinciaController extends BaseControllerImp<Provincia, ProvinciaDto,Long, ProvinciaFacadeImp> {
    public ProvinciaController(ProvinciaFacadeImp facade) {
        super(facade);
    }

    private static final Logger logger = LoggerFactory.getLogger(ProvinciaController.class);


    @GetMapping("findByPais/{idPais}")
    public ResponseEntity<List<ProvinciaDto>> getByProvincia(@PathVariable Long idPais) {
        logger.info("INICIO GET BY PROVINCIA");
        return ResponseEntity.ok(facade.findByPaisId(idPais));
    }
}
