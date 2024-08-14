package com.example.menudigital.bussines.services;

import com.example.menudigital.bussines.services.base.BaseService;
import com.example.menudigital.domain.entities.Categoria;
import com.example.menudigital.domain.entities.Sucursal;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

public interface SucursalService extends BaseService<Sucursal, Long> {
    List<Categoria> findCategoriasBySucursalId(Long sucursalId);
    boolean existsSucursalByEsCasaMatriz(Long empresaId);
    @Named("getSucursalesByEmpresaId")
    Set<Sucursal> findAllByEmpresaId(Long id);
}
