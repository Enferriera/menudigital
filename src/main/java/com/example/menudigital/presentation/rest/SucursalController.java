package com.example.menudigital.presentation.rest;



import com.example.menudigital.bussines.facade.Impl.SucursalFacadeImp;
import com.example.menudigital.bussines.services.base.BaseServiceImp;
import com.example.menudigital.domain.dtos.categoriaDto.CategoriaDto;
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


    @Override
    @PostMapping()
    public ResponseEntity<SucursalDto> create(@RequestBody SucursalDto dto) {
        return ResponseEntity.ok().body(facade.createSucursal(dto));
    }


    @Override
    @PutMapping("/{id}")
    public ResponseEntity<SucursalDto> edit( @RequestBody SucursalDto dto,@PathVariable Long id){
       logger.info("Editing Sucursal "+id);
       logger.info("Editing Sucursal "+dto.getId());
        return ResponseEntity.ok().body(facade.updateSucursal(id, dto));
    }


    @GetMapping("/categorias/{id}")
    public List<CategoriaDto> getCategoriasBySucursalId(@PathVariable Long id) {
        return facade.findCategoriasBySucursalId(id);
    }


    @GetMapping("/esCasaMatriz/{id}")
    public ResponseEntity<Boolean> esCasaMatriz(@PathVariable Long id) {
        return ResponseEntity.ok().body(facade.existsSucursalByEsCasaMatriz(id));
    }


    @GetMapping("/porEmpresa/{id}")
    public List<SucursalDto> getSucursalesByEmpresaId(@PathVariable Long id) {
        return facade.findAllByEmpresaId(id);
    }
}
