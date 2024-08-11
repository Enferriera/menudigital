package com.example.menudigital.domain.dtos.sucursalDto;

import com.example.menudigital.domain.dtos.BaseDto;
import com.example.menudigital.domain.dtos.domicilioDto.DomicilioCreateDto;
import com.example.menudigital.domain.dtos.domicilioDto.DomicilioDto;
import com.example.menudigital.domain.dtos.empresaDto.EmpresaDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SucursalCreateDto extends BaseDto {
    private String nombre;
    @Schema(type = "string", format = "time", pattern = "HH:mm:ss")
    private LocalTime horarioApertura;
    @Schema(type = "string", format = "time", pattern = "HH:mm:ss")
    private LocalTime horarioCierre;
    private boolean esCasaMatriz;
    private DomicilioCreateDto domicilio;
    private Long idEmpresa;
    private double latitud;
    private double longitud;
    private String logo;
}
