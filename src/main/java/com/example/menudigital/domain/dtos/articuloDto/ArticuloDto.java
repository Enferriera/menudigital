package com.example.menudigital.domain.dtos.articuloDto;

import com.example.menudigital.domain.dtos.BaseDto;
import com.example.menudigital.domain.dtos.alergeno.AlergenoDto;
import com.example.menudigital.domain.dtos.categoriaDto.CategoriaShortDto;
import com.example.menudigital.domain.entities.Alergeno;
import com.example.menudigital.domain.entities.Categoria;
import com.example.menudigital.domain.entities.ImagenArticulo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.NotAudited;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArticuloDto extends BaseDto {
    protected String denominacion;
    protected Double precioVenta;
    protected String descripcion;
    protected boolean habilitado = true;
    protected String codigo;


    protected Set<ImagenArticulo> imagenes = new HashSet<>();

    protected CategoriaShortDto categoria;

    protected Set<AlergenoDto> alergenos=new HashSet<>();
}
