package com.example.menudigital.domain.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BaseDto {
    protected Long id;
    protected boolean eliminado;
}
