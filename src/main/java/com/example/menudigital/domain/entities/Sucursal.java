package com.example.menudigital.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Audited
@SuperBuilder
public class Sucursal extends  Base{

    private String nombre;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private boolean esCasaMatriz;


    @OneToOne(cascade = CascadeType.ALL)
    private Domicilio domicilio;


    @ManyToOne
    private Empresa empresa;


}
