package com.example.menudigital.presentation.rest;


import com.example.menudigital.bussines.facade.Impl.DomicilioFacadeImp;
import com.example.menudigital.domain.dtos.domicilioDto.DomicilioCreateDto;
import com.example.menudigital.domain.dtos.domicilioDto.DomicilioDto;
import com.example.menudigital.domain.entities.Domicilio;
import com.example.menudigital.presentation.rest.Base.BaseControllerImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/domicilios")
@CrossOrigin(origins="*")
public class DomicilioController extends BaseControllerImp<Domicilio, DomicilioDto,Long, DomicilioFacadeImp> {
    public DomicilioController(DomicilioFacadeImp facade) {
        super(facade);
    }

    @PostMapping("/create")
    public ResponseEntity<DomicilioDto> createDomicilio(@RequestBody DomicilioCreateDto dto) {
        return ResponseEntity.ok().body(facade.createDomicilio(dto));
    }

}
