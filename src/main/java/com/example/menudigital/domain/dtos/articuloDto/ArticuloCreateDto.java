package com.example.menudigital.domain.dtos.articuloDto;

import com.example.menudigital.domain.dtos.BaseDto;
import com.example.menudigital.domain.dtos.alergeno.AlergenoDto;
import com.example.menudigital.domain.dtos.categoriaDto.CategoriaShortDto;
import com.example.menudigital.domain.entities.ImagenArticulo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticuloCreateDto extends BaseDto {
    protected String denominacion;
    protected Double precioVenta;
    protected String descripcion;
    protected boolean habilitado = true;
    protected String codigo;

    protected Set<ImagenArticulo> imagenes = new HashSet<>();

    protected Long idCategoria;

    protected Set<Long> idAlergenos=new HashSet<>();
}
