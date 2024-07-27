package com.example.menudigital.domain.dtos.empresaDto;

import com.example.menudigital.domain.dtos.BaseDto;
import com.example.menudigital.domain.dtos.sucursalDto.SucursalDto;
import com.example.menudigital.domain.dtos.sucursalDto.SucursalShortDto;
import com.example.menudigital.domain.entities.Sucursal;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EmpresaDto extends BaseDto {
    private String nombre;
    private String razonSocial;
    private Long cuit;
    private String logo;

}
