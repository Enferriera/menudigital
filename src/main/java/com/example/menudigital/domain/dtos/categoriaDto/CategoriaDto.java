package com.example.menudigital.domain.dtos.categoriaDto;

import com.example.menudigital.domain.dtos.BaseDto;
import com.example.menudigital.domain.dtos.articuloDto.ArticuloShortDto;
import com.example.menudigital.domain.dtos.sucursalDto.SucursalShortDto;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoriaDto extends BaseDto {
    private String denominacion;

   // private Set<ArticuloShortDto> articulos = new HashSet<>();

    private Set<CategoriaShortDto> subCategorias = new HashSet<>();

    private CategoriaShortDto categoriaPadre;

    private Set<SucursalShortDto> sucursales = new HashSet<>();
}
