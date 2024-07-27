package com.example.menudigital.domain.dtos.categoriaDto;

import com.example.menudigital.domain.dtos.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoriaShortDto extends BaseDto {
    private String denominacion;
}
