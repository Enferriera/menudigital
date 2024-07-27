package com.example.menudigital.domain.dtos.sucursalDto;

import com.example.menudigital.domain.dtos.BaseDto;
import com.example.menudigital.domain.dtos.categoriaDto.CategoriaShortDto;
import com.example.menudigital.domain.dtos.domicilioDto.DomicilioDto;
import com.example.menudigital.domain.dtos.empresaDto.EmpresaDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SucursalDto extends BaseDto {
    private String nombre;
    @Schema(type = "string", format = "time", pattern = "HH:mm:ss")
    private LocalTime horarioApertura;
    @Schema(type = "string", format = "time", pattern = "HH:mm:ss")
    private LocalTime horarioCierre;
    private boolean esCasaMatriz;

    private DomicilioDto domicilio;

    private EmpresaDto empresa;

    private Set<CategoriaShortDto> categorias = new HashSet<>();
}
