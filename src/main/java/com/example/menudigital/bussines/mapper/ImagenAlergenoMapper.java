package com.example.menudigital.bussines.mapper;

import com.example.menudigital.domain.dtos.ImagenDto;
import com.example.menudigital.domain.entities.ImagenAlergeno;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImagenAlergenoMapper extends BaseMapper<ImagenAlergeno, ImagenDto> {
}
