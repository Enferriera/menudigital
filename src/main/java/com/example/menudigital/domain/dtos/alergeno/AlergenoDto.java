package com.example.menudigital.domain.dtos.alergeno;

import com.example.menudigital.domain.dtos.BaseDto;
import com.example.menudigital.domain.dtos.ImagenDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AlergenoDto extends BaseDto {
    private String denominacion;
    private ImagenDto imagen;
}
