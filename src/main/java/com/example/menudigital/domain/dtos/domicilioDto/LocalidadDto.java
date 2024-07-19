package com.example.menudigital.domain.dtos.domicilioDto;

import com.example.menudigital.domain.dtos.BaseDto;
import com.example.menudigital.domain.entities.Provincia;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private ProvinciaDto provincia;
}
