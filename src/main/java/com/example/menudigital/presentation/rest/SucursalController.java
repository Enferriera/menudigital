package com.example.menudigital.presentation.rest;



import com.example.menudigital.bussines.facade.Impl.SucursalFacadeImp;
import com.example.menudigital.bussines.services.base.BaseServiceImp;
import com.example.menudigital.domain.dtos.categoriaDto.CategoriaDto;
import com.example.menudigital.domain.dtos.sucursalDto.SucursalCreateDto;
import com.example.menudigital.domain.dtos.sucursalDto.SucursalDto;
import com.example.menudigital.domain.entities.Sucursal;
import com.example.menudigital.presentation.rest.Base.BaseControllerImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sucursales")
@CrossOrigin(origins="*")
public class SucursalController extends BaseControllerImp<Sucursal, SucursalDto,Long, SucursalFacadeImp> {
    private static final Logger logger = LoggerFactory.getLogger(BaseServiceImp.class);
    public SucursalController(SucursalFacadeImp facade) {
        super(facade);
    }



    @PostMapping("/create")
    public ResponseEntity<SucursalDto> create(@RequestBody SucursalCreateDto dto) {
        return ResponseEntity.ok().body(facade.createSucursal(dto));
    }



    @PutMapping("/update/{idSucursal}")
    public ResponseEntity<SucursalDto> edit( @RequestBody SucursalCreateDto dto,@PathVariable Long idSucursal){
        return ResponseEntity.ok().body(facade.updateSucursal(dto,idSucursal));
    }




    @GetMapping("/existCasaMatriz/{idEmpresa}")
    public ResponseEntity<Boolean> existeCasaMatriz(@PathVariable Long idEmpresa) {
        return ResponseEntity.ok().body(facade.existsSucursalByEsCasaMatriz(idEmpresa));
    }


    @GetMapping("/porEmpresa/{idEmpresa}")
    public List<SucursalDto> getSucursalesByEmpresaId(@PathVariable Long idEmpresa) {
        return facade.findAllByEmpresaId(idEmpresa);
    }
}
