package com.example.menudigital.presentation.rest;


import com.example.menudigital.bussines.facade.Impl.EmpresaFacadeImpl;
import com.example.menudigital.domain.dtos.empresaDto.EmpresaDto;
import com.example.menudigital.domain.dtos.empresaDto.EmpresaLargeDto;
import com.example.menudigital.domain.entities.Empresa;
import com.example.menudigital.presentation.rest.Base.BaseControllerImp;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresas")
@CrossOrigin(origins = {"https://dashboard-menu-project.vercel.app", "http://localhost:5173"})

public class EmpresaController extends BaseControllerImp<Empresa, EmpresaDto, Long, EmpresaFacadeImpl> {
    public EmpresaController(EmpresaFacadeImpl facade) {
        super(facade);
    }


    @PutMapping("/addSucursal")
    public ResponseEntity<EmpresaLargeDto> addSucursal(@RequestParam Long idEmpresa, @RequestParam Long idSucursal){
        return ResponseEntity.ok(facade.addSucursal(idEmpresa,idSucursal));
    }


    @GetMapping("/full/{idEmpresa}")
    public ResponseEntity<EmpresaLargeDto> getEmpresaSucursales(@PathVariable Long idEmpresa) {
        return ResponseEntity.ok(facade.findWithSucursalesById(idEmpresa));
    }


}
