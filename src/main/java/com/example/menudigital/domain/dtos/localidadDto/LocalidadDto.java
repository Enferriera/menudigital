package com.example.menudigital.domain.dtos.localidadDto;

import com.example.menudigital.domain.dtos.BaseDto;
import com.example.menudigital.domain.dtos.provinciaDto.ProvinciaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LocalidadDto extends BaseDto {
    private String nombre;
}
