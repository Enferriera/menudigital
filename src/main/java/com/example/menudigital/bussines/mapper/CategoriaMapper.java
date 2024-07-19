package com.example.menudigital.bussines.mapper;


import com.example.menudigital.bussines.services.CategoriaService;
import com.example.menudigital.bussines.services.SucursalService;
import com.example.menudigital.domain.dtos.categoriaDto.CategoriaCreateDto;
import com.example.menudigital.domain.dtos.categoriaDto.CategoriaDto;
import com.example.menudigital.domain.dtos.categoriaDto.CategoriaShortDto;
import com.example.menudigital.domain.entities.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ArticuloMapper.class, SucursalMapper.class, SucursalService.class, CategoriaService.class})
public interface CategoriaMapper extends BaseMapper<Categoria, CategoriaDto>{

    CategoriaShortDto toShortDTO(Categoria source);



    @Mappings({
            @Mapping(source = "idSucursales", target = "sucursales", qualifiedByName = "getById",defaultExpression = "java(new java.util.HashSet<>())"),
            @Mapping(source = "idCategoriaPadre", target = "categoriaPadre", qualifiedByName = "getById",defaultExpression = "java(null)"),
    })
    Categoria toEntityCreate(CategoriaCreateDto source);


}
