package com.example.menudigital.bussines.mapper;

import com.example.menudigital.domain.dtos.empresaDto.EmpresaDto;
import com.example.menudigital.domain.dtos.empresaDto.EmpresaLargeDto;
import com.example.menudigital.domain.entities.Empresa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmpresaMapper extends BaseMapper<Empresa, EmpresaDto> {
    EmpresaLargeDto toLargeDto(Empresa empresa);
}
