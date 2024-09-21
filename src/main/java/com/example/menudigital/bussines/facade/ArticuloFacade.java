package com.example.menudigital.bussines.facade;

import com.example.menudigital.bussines.facade.Base.BaseFacade;
import com.example.menudigital.domain.dtos.articuloDto.ArticuloCreateDto;
import com.example.menudigital.domain.dtos.articuloDto.ArticuloDto;
import com.example.menudigital.domain.entities.Articulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ArticuloFacade extends BaseFacade<ArticuloDto, Long> {
    public List<ArticuloDto> findAllBySucursalId(Long idSucusal);

    public ArticuloDto createArticulo(ArticuloCreateDto dto);

    public ArticuloDto updateArticulo(ArticuloCreateDto dto,Long id);

    Page<ArticuloDto> findAllBySucursalIdPaged(Long idSucursal, Pageable pageable);

    List<ArticuloDto> findAllByCategoriaId(Long idCategoria);

    Page<ArticuloDto> findAllByCategoriaIdPaged(Long idCategoria, Pageable pageable);

    List<ArticuloDto> findAllHabilitadoBySucursalId(Long sucursalId);
    boolean cambiarHabilitado(Long idArticulo);

    //Imagenes
    // Método para obtener todas las imágenes almacenadas
    ResponseEntity<List<Map<String, Object>>> getAllImagesByArticuloId(Long id);
    // Método para subir imágenes al sistema
    ResponseEntity<String> uploadImages(MultipartFile[] files, Long id);
    // Método para eliminar una imagen por su identificador público y UUID
    ResponseEntity<String> deleteImage(String publicId, Long id);
}
