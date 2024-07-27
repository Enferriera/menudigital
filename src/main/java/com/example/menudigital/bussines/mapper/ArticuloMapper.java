package com.example.menudigital.bussines.mapper;


import com.example.menudigital.bussines.services.AlergenoService;
import com.example.menudigital.bussines.services.CategoriaService;
import com.example.menudigital.domain.dtos.articuloDto.ArticuloCreateDto;
import com.example.menudigital.domain.dtos.articuloDto.ArticuloDto;
import com.example.menudigital.domain.dtos.articuloDto.ArticuloShortDto;
import com.example.menudigital.domain.entities.Articulo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring",uses={ImagenArticuloMapper.class, CategoriaService.class, AlergenoMapper.class,CategoriaMapper.class, AlergenoService.class})
public interface ArticuloMapper extends BaseMapper<Articulo, ArticuloDto> {

  @Mappings({
          @Mapping(source="idCategoria", target = "categoria", qualifiedByName = "getById"),
          @Mapping(source= "idAlergenos", target = "alergenos", qualifiedByName = "getById"),
  })

    Articulo toEntityCreate(ArticuloCreateDto articuloCreateDto);

    ArticuloShortDto toShortDTO(Articulo articulo);
}
