package com.example.menudigital.bussines.facade;

import com.example.menudigital.bussines.facade.Base.BaseFacade;
import com.example.menudigital.domain.dtos.categoriaDto.CategoriaCreateDto;
import com.example.menudigital.domain.dtos.categoriaDto.CategoriaDto;
import com.example.menudigital.domain.entities.Categoria;

import java.util.List;

public interface CategoriaFacade extends BaseFacade<CategoriaDto, Long> {
    List<CategoriaDto> findAllCategoriasBySucursalId(Long idSucursal);
    CategoriaDto createCategoria(CategoriaCreateDto dto);
    void deleteCategoriaInSucursales(Long idCategoria, Long idSucursal);
    List<CategoriaDto> findAllCategoriasByEmpresaId( Long idEmpresa);
    CategoriaDto updateCategoria(Long id, CategoriaCreateDto dto);
    List<CategoriaDto> findAllSubCategoriasByCategoriaPadreId(Long id);
}
