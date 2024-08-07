package com.example.menudigital.bussines.mapper;


import com.example.menudigital.domain.dtos.localidadDto.LocalidadDto;
import com.example.menudigital.domain.entities.Localidad;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProvinciaMapper.class})
public interface LocalidadMapper extends BaseMapper<Localidad, LocalidadDto> {
}
