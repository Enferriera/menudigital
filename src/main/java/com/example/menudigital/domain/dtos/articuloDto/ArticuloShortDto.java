package com.example.menudigital.domain.dtos.articuloDto;

import com.example.menudigital.domain.dtos.BaseDto;
import com.example.menudigital.domain.dtos.ImagenDto;
import com.example.menudigital.domain.dtos.alergeno.AlergenoDto;
import com.example.menudigital.domain.dtos.categoriaDto.CategoriaShortDto;
import com.example.menudigital.domain.entities.Alergeno;
import com.example.menudigital.domain.entities.Categoria;
import com.example.menudigital.domain.entities.ImagenArticulo;

import lombok.*;


import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArticuloShortDto extends BaseDto {
    protected String denominacion;
    protected Double precioVenta;
    protected String descripcion;
    protected boolean habilitado;
    protected String codigo;


    protected Set<ImagenDto> imagenes = new HashSet<>();

    protected Set<AlergenoDto> alergenos=new HashSet<>();
}
