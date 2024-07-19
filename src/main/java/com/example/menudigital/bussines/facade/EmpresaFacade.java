package com.example.menudigital.bussines.facade;


import com.example.menudigital.bussines.facade.Base.BaseFacade;
import com.example.menudigital.domain.dtos.empresaDto.EmpresaDto;
import com.example.menudigital.domain.dtos.empresaDto.EmpresaLargeDto;

public interface EmpresaFacade extends BaseFacade<EmpresaDto, Long> {
    EmpresaLargeDto addSucursal(Long idEmpresa, Long idSucursal);
    EmpresaLargeDto findWithSucursalesById(Long id);


}
