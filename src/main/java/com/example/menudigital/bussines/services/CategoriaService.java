package com.example.menudigital.bussines.services;

import com.example.menudigital.bussines.services.base.BaseService;
import com.example.menudigital.domain.entities.Categoria;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaService extends BaseService<Categoria, Long> {
    List<Categoria> findAllCategoriasPadreBySucursalId(Long idSucursal);
    void deleteCategoriaInSucursales(Long idCategoria, Long idSucursal);
    List<Categoria> findAllCategoriasByEmpresaId( Long idEmpresa);
    List<Categoria> findAllSubCategoriasByCategoriaPadreId(Long id);

    List<Categoria> findAllCategoriasBySucursalId(Long idSucursal);
}
