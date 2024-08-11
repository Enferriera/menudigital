package com.example.menudigital.presentation.rest;


import com.example.menudigital.bussines.facade.Impl.PaisFacadeImp;
import com.example.menudigital.domain.dtos.paisDto.PaisDto;
import com.example.menudigital.domain.entities.Pais;
import com.example.menudigital.presentation.rest.Base.BaseControllerImp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paises")
@CrossOrigin(origins={" https://dashboard-menu-project.vercel.app/",
        "link: http://localhost:5173"})
public class PaisController extends BaseControllerImp<Pais, PaisDto,Long, PaisFacadeImp> {

    public PaisController(PaisFacadeImp facade) {
        super(facade);
    }
}
