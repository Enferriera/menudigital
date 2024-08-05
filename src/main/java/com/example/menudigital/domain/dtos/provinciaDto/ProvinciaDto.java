package com.example.menudigital.domain.dtos.provinciaDto;

import com.example.menudigital.domain.dtos.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProvinciaDto extends BaseDto {
    private String nombre;
}
