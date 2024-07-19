package com.example.menudigital.presentation.rest;


import com.example.menudigital.bussines.facade.Impl.DomicilioFacadeImp;
import com.example.menudigital.domain.dtos.domicilioDto.DomicilioDto;
import com.example.menudigital.domain.entities.Domicilio;
import com.example.menudigital.presentation.rest.Base.BaseControllerImp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domicilios")
@CrossOrigin(origins="*")
public class DomicilioController extends BaseControllerImp<Domicilio, DomicilioDto,Long, DomicilioFacadeImp> {
    public DomicilioController(DomicilioFacadeImp facade) {
        super(facade);
    }

}
