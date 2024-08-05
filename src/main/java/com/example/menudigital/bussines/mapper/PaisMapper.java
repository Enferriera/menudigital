package com.example.menudigital.bussines.mapper;


import com.example.menudigital.domain.dtos.paisDto.PaisDto;
import com.example.menudigital.domain.entities.Pais;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaisMapper extends BaseMapper<Pais, PaisDto>{

}
