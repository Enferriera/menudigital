package com.example.menudigital.bussines.mapper;


import com.example.menudigital.domain.dtos.provinciaDto.ProvinciaDto;
import com.example.menudigital.domain.entities.Provincia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PaisMapper.class})
public interface ProvinciaMapper extends BaseMapper<Provincia, ProvinciaDto>{

}
