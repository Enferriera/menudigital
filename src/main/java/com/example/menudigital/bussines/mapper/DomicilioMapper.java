package com.example.menudigital.bussines.mapper;

import com.example.menudigital.domain.dtos.domicilioDto.DomicilioDto;
import com.example.menudigital.domain.entities.Domicilio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {LocalidadMapper.class, ProvinciaMapper.class, PaisMapper.class})
public interface DomicilioMapper extends BaseMapper<Domicilio, DomicilioDto> {
    
}
