package com.example.menudigital.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.HashSet;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Setter
@SuperBuilder
@Audited
public class Articulo extends Base {

    private String denominacion;
    private Double precioVenta;
    @Builder.Default
    private boolean habilitado = true;
    private String codigo;


    @OneToMany
    @JoinColumn(name = "articulo_id")
    @Builder.Default
    @NotAudited
    private Set<ImagenArticulo> imagenes = new HashSet<>();



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_id")
    @JsonIgnoreProperties("articulos")
    private Categoria categoria;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "articulo_alergenos",
            joinColumns = @JoinColumn(name = "articulo_id"),
            inverseJoinColumns = @JoinColumn(name = "alergenos_id"))
    @Builder.Default
    private Set<Alergeno> alergenos=new HashSet<>();
}

