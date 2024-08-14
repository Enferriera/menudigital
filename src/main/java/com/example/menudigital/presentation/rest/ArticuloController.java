package com.example.menudigital.presentation.rest;

import com.example.menudigital.bussines.facade.Impl.ArticuloFacadeImpl;
import com.example.menudigital.domain.dtos.articuloDto.ArticuloCreateDto;
import com.example.menudigital.domain.dtos.articuloDto.ArticuloDto;
import com.example.menudigital.domain.entities.Articulo;
import com.example.menudigital.presentation.rest.Base.BaseControllerImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articulos")
@CrossOrigin(origins = {"https://dashboard-menu-project.vercel.app", "http://localhost:5173"})

public class ArticuloController extends BaseControllerImp<Articulo, ArticuloDto,Long, ArticuloFacadeImpl> {
    public ArticuloController(ArticuloFacadeImpl facade) {
        super(facade);
    }

    @GetMapping("/porSucursal/{idSucursal}")
    public ResponseEntity<List<ArticuloDto>> getArticulosPorSucursal(@PathVariable Long idSucursal) {
        return ResponseEntity.ok().body(facade.findAllBySucursalId(idSucursal));
    }

    @GetMapping("/porCategoria/{idCategoria}")
    public ResponseEntity<List<ArticuloDto>> getArticulosPorCategoria(@PathVariable Long idCategoria) {
        return ResponseEntity.ok().body(facade.findAllByCategoriaId(idCategoria));
    }

    @GetMapping("/pagedPorSucursal/{idSucursal}")
    public ResponseEntity<Page<ArticuloDto>> getArticulosPorSucursalPaged(@PathVariable Long idSucursal, Pageable pageable) {
        return ResponseEntity.ok().body(facade.findAllBySucursalIdPaged(idSucursal, pageable));
    }

    @GetMapping("/pagedPorCategoria/{idCategoria}")
    public ResponseEntity<Page<ArticuloDto>> getArticulosPorCategoriaPaged(@PathVariable Long idCategoria, Pageable pageable) {
        return ResponseEntity.ok().body(facade.findAllByCategoriaIdPaged(idCategoria, pageable));
    }

    @PostMapping("/create")
    public ResponseEntity<ArticuloDto> create(@RequestBody ArticuloCreateDto articuloDto) {
        return ResponseEntity.ok().body(facade.createArticulo(articuloDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ArticuloDto> update(@PathVariable Long id, @RequestBody ArticuloCreateDto articuloDto) {
        return ResponseEntity.ok().body(facade.updateArticulo(articuloDto, id));
    }

    @GetMapping("/getArticulosHabilitadosBySucursal/{sucursalId}")
    public ResponseEntity<List<ArticuloDto>> findAllHabilitadoBySucursalId(@PathVariable Long sucursalId){
        return ResponseEntity.ok().body(facade.findAllHabilitadoBySucursalId(sucursalId));
    }

    @PutMapping("/changeHabilitado/{articuloId}")
    public ResponseEntity<Boolean> chageHabilitado(@PathVariable Long articuloId){
        return ResponseEntity.ok().body(facade.cambiarHabilitado(articuloId));
    }
}
