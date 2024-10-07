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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/articulos")
@CrossOrigin(origins = "*")

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

    @PostMapping("/uploads")
    public ResponseEntity<String> uploadImages(
            @RequestParam(value = "uploads") MultipartFile[] files,
            @RequestParam(value = "id") Long idArticulo) {
        try {
            return facade.uploadImages(files, idArticulo); // Llama al método del servicio para subir imágenes
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Manejo básico de errores, se puede mejorar para devolver una respuesta más específica
        }
    }

    @PostMapping("/deleteImg")
    public ResponseEntity<String> deleteById(
            @RequestParam(value = "publicId") String publicId,
            @RequestParam(value = "id") Long id) {
        try {
            return facade.deleteImage(publicId, id); // Llama al método del servicio para eliminar la imagen
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Invalid UUID format"); // Respuesta HTTP 400 si el UUID no es válido
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred"); // Respuesta HTTP 500 si ocurre un error inesperado
        }
    }

    // Método GET para obtener todas las imágenes almacenadas
    @GetMapping("/getImagesByArticuloId/{id}")
    public ResponseEntity<?> getAllImages(@PathVariable Long id) {
        try {
            return facade.getAllImagesByArticuloId(id); // Llama al método del servicio para obtener todas las imágenes
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Manejo básico de errores, se puede mejorar para devolver una respuesta más específica
        }
    }
}
