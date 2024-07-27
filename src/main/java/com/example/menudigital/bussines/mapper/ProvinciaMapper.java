package com.example.menudigital.bussines.mapper;


import com.example.menudigital.domain.dtos.domicilioDto.ProvinciaDto;
import com.example.menudigital.domain.entities.Provincia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProvinciaMapper extends BaseMapper<Provincia, ProvinciaDto>{

}
