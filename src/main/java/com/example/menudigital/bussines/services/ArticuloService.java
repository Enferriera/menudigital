package com.example.menudigital.bussines.services;

import com.example.menudigital.bussines.services.base.BaseService;
import com.example.menudigital.domain.entities.Articulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticuloService extends BaseService<Articulo, Long> {
    boolean existsArticuloByAlergeno(Long idAlergeno);
    List<Articulo> findAllBySucursalId( Long idSucusal);
    Page<Articulo> findAllBySucursalIdPaged(Long idSucursal, Pageable pageable);
    List<Articulo> findAllByCategoriaId(Long idCategoria);
    Page<Articulo> findAllByCategoriaIdPaged(Long idCategoria, Pageable pageable);
    List<Articulo> findAllHabilitadoBySucursalId(Long sucursalId);
    boolean cambiarHabilitado(Long idArticulo);
}
