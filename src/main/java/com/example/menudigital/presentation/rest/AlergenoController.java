package com.example.menudigital.presentation.rest;

import com.example.menudigital.bussines.facade.Impl.AlergenoFacadeImpl;
import com.example.menudigital.domain.dtos.alergeno.AlergenoDto;
import com.example.menudigital.domain.entities.Alergeno;
import com.example.menudigital.presentation.rest.Base.BaseControllerImp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alergenos")
@CrossOrigin(origins = {"https://dashboard-menu-project.vercel.app", "http://localhost:5173"})

public class AlergenoController extends BaseControllerImp<Alergeno, AlergenoDto, Long, AlergenoFacadeImpl> {
    public AlergenoController(AlergenoFacadeImpl facade) {
        super(facade);
    }
}
