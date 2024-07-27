package com.example.menudigital.bussines.facade;



import com.example.menudigital.bussines.facade.Base.BaseFacade;
import com.example.menudigital.domain.dtos.sucursalDto.SucursalCreateDto;
import com.example.menudigital.domain.dtos.sucursalDto.SucursalDto;

import java.util.List;

public interface SucursalFacade extends BaseFacade<SucursalDto, Long> {
    SucursalDto createSucursal(SucursalCreateDto dto);
    SucursalDto updateSucursal(SucursalCreateDto dto,Long id);
    boolean existsSucursalByEsCasaMatriz(Long id);
    List<SucursalDto> findAllByEmpresaId( Long id);
}
