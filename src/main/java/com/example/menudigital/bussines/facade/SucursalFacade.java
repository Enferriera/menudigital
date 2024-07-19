package com.example.menudigital.bussines.facade;



import com.example.menudigital.bussines.facade.Base.BaseFacade;
import com.example.menudigital.domain.dtos.sucursalDto.SucursalDto;

import java.util.List;

public interface SucursalFacade extends BaseFacade<SucursalDto, Long> {
    SucursalDto createSucursal(SucursalDto dto);
    SucursalDto updateSucursal(Long id,SucursalDto dto);
    boolean existsSucursalByEsCasaMatriz(Long id);
    List<SucursalDto> findAllByEmpresaId( Long id);
}
