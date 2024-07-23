package com.example.menudigital.bussines.mapper;

import com.example.menudigital.domain.dtos.alergeno.AlergenoDto;
import com.example.menudigital.domain.entities.Alergeno;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses= {ImagenAlergenoMapper.class})
public interface AlergenoMapper extends BaseMapper<Alergeno, AlergenoDto> {
}
