package com.example.menudigital.bussines.mapper;

import com.example.menudigital.domain.dtos.ImagenDto;
import com.example.menudigital.domain.entities.ImagenArticulo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImagenArticuloMapper extends BaseMapper<ImagenArticulo, ImagenDto> {
}
