package com.example.menudigital.domain.dtos.domicilioDto;

import com.example.menudigital.domain.dtos.BaseDto;
import com.example.menudigital.domain.dtos.localidadDto.LocalidadDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DomicilioDto extends BaseDto {
    private String calle;
    private Integer numero;
    private Integer cp;
    private Integer piso;
    private Integer nroDpto;

    private LocalidadDto localidad;
}
