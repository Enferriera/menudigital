package com.example.menudigital.bussines.services;

import com.example.menudigital.bussines.services.base.BaseService;
import com.example.menudigital.domain.entities.Categoria;
import com.example.menudigital.domain.entities.Sucursal;

import java.util.List;

public interface SucursalService extends BaseService<Sucursal, Long> {
    List<Categoria> findCategoriasBySucursalId(Long sucursalId);
    boolean existsSucursalByEsCasaMatriz(Long empresaId);
    List<Sucursal> findAllByEmpresaId(Long id);
}
