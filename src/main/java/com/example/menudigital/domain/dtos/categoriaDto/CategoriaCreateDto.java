package com.example.menudigital.domain.dtos.categoriaDto;


import com.example.menudigital.domain.dtos.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoriaCreateDto extends BaseDto {
    private String denominacion;
    private Set<Long> idSucursales;
    private Long idCategoriaPadre;

}



