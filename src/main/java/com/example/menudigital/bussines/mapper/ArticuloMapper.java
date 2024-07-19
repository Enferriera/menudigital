package com.example.menudigital.bussines.mapper;

import com.example.menudigital.domain.dtos.articuloDto.ArticuloDto;
import com.example.menudigital.domain.entities.Articulo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticuloMapper extends BaseMapper<Articulo, ArticuloDto> {
}
