package com.example.menudigital.presentation.rest;


import com.example.menudigital.bussines.facade.Impl.CategoriaFacadeImpl;
import com.example.menudigital.domain.dtos.categoriaDto.CategoriaCreateDto;
import com.example.menudigital.domain.dtos.categoriaDto.CategoriaDto;
import com.example.menudigital.domain.entities.Categoria;
import com.example.menudigital.presentation.rest.Base.BaseControllerImp;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins="*")
public class CategoriaController extends BaseControllerImp<Categoria, CategoriaDto, Long, CategoriaFacadeImpl> {

    public CategoriaController(CategoriaFacadeImpl facade) {
        super(facade);
    }





    @PutMapping("/bajaPorSucursal/{idCategoria}/{idSucursal}")
    public void deleteById(@PathVariable Long idCategoria, @PathVariable Long idSucursal) {
        facade.deleteCategoriaInSucursales(idCategoria, idSucursal);
    }


    @PostMapping("/create")
    public ResponseEntity<CategoriaDto> createNew(@RequestBody CategoriaCreateDto dto) {
        return ResponseEntity.ok(facade.createCategoria(dto));
    }



    @GetMapping("/allCategoriasPorSucursal/{idSucursal}")
    public ResponseEntity<List<CategoriaDto>> getAllCategoriaBySucursalId(@PathVariable Long idSucursal){
        return ResponseEntity.ok().body(facade.findAllCategoriasBySucursalId(idSucursal));
    }

    @GetMapping("/allCategoriasPorEmpresa/{idEmpresa}")
    public ResponseEntity<List<CategoriaDto>> getAllCategoriaByEmpresaId(@PathVariable Long idEmpresa){
        return ResponseEntity.ok().body(facade.findAllCategoriasByEmpresaId(idEmpresa));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoriaDto> updateCategoria(@PathVariable Long id, @RequestBody CategoriaCreateDto dto) {
        return ResponseEntity.ok().body(facade.updateCategoria(id, dto));
    }
}
