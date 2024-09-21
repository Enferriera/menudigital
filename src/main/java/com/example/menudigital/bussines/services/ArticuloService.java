package com.example.menudigital.bussines.services;

import com.example.menudigital.bussines.services.base.BaseService;
import com.example.menudigital.domain.entities.Articulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ArticuloService extends BaseService<Articulo, Long> {
    boolean existsArticuloByAlergeno(Long idAlergeno);
    List<Articulo> findAllBySucursalId( Long idSucusal);
    Page<Articulo> findAllBySucursalIdPaged(Long idSucursal, Pageable pageable);
    List<Articulo> findAllByCategoriaId(Long idCategoria);
    Page<Articulo> findAllByCategoriaIdPaged(Long idCategoria, Pageable pageable);
    List<Articulo> findAllHabilitadoBySucursalId(Long sucursalId);
    boolean cambiarHabilitado(Long idArticulo);

    //Imagenes
    // Método para obtener todas las imágenes almacenadas
    ResponseEntity<List<Map<String, Object>>> getAllImagesByArticuloId(Long id);
    // Método para subir imágenes al sistema
    ResponseEntity<String> uploadImages(MultipartFile[] files, Long id);
    // Método para eliminar una imagen por su identificador público y Long
    ResponseEntity<String> deleteImage(String publicId, Long id);
}
