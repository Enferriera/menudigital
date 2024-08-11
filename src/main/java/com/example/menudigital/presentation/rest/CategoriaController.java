package com.example.menudigital.presentation.rest;


import com.example.menudigital.bussines.facade.Impl.CategoriaFacadeImpl;
import com.example.menudigital.domain.dtos.categoriaDto.CategoriaCreateDto;
import com.example.menudigital.domain.dtos.categoriaDto.CategoriaDto;
import com.example.menudigital.domain.dtos.categoriaDto.CategoriaShortDto;
import com.example.menudigital.domain.entities.Categoria;
import com.example.menudigital.presentation.rest.Base.BaseControllerImp;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = {"https://dashboard-menu-project.vercel.app", "http://localhost:5173"})

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


    //OBTIENE SOLO CATEGORIAS PADRE DE UNA SUCURSAL
    @GetMapping("/allCategoriasPadrePorSucursal/{idSucursal}")
    public ResponseEntity<List<CategoriaDto>> getAllCategoriaPadreBySucursalId(@PathVariable Long idSucursal){
        return ResponseEntity.ok().body(facade.findAllCategoriasPadreBySucursalId(idSucursal));
    }

    //OBTIENE TODAS LAS CATEGORIAS DE UNA SUCURSAL
    @GetMapping("/allCategoriasPorSucursal/{idSucursal}")
    public ResponseEntity<List<CategoriaShortDto>> getAllCategoriaBySucursalId(@PathVariable Long idSucursal){
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


    @GetMapping("/allSubCategoriasPorCategoriaPadre/{idCategoriaPadre}")
    public ResponseEntity<List<CategoriaDto>> getAllSubCategoriaByCategoriaPadreId(@PathVariable Long idCategoriaPadre) {
        return ResponseEntity.ok().body(facade.findAllSubCategoriasByCategoriaPadreId(idCategoriaPadre));
    }

    @GetMapping("/allSubCategoriasPorSucursal/{idSucursal}")
    public ResponseEntity<List<CategoriaDto>> getAllSubCategoriaBySucursalId(@PathVariable Long idSucursal) {
        return ResponseEntity.ok().body(facade.findSubcategoriasBySucursalId(idSucursal));
    }

}
