package com.example.menudigital.bussines.mapper;

import com.example.menudigital.bussines.services.LocalidadService;
import com.example.menudigital.domain.dtos.domicilioDto.DomicilioCreateDto;
import com.example.menudigital.domain.dtos.domicilioDto.DomicilioDto;
import com.example.menudigital.domain.entities.Domicilio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {LocalidadMapper.class, ProvinciaMapper.class, PaisMapper.class, LocalidadService.class})
public interface DomicilioMapper extends BaseMapper<Domicilio, DomicilioDto> {

    @Mapping(source="idLocalidad", target = "localidad", qualifiedByName = "getById")
    Domicilio toEntityCreate(DomicilioCreateDto dto);
}
