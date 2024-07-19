package com.example.menudigital.bussines.mapper;

import com.example.menudigital.domain.dtos.domicilioDto.DomicilioDto;
import com.example.menudigital.domain.entities.Domicilio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DomicilioMapper extends BaseMapper<Domicilio, DomicilioDto> {
}
