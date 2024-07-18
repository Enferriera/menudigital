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

    protected String denominacion;
    protected Double precioVenta;
    protected String descripcion;
    @Builder.Default
    protected boolean habilitado = true;
    protected String codigo;


    @OneToMany
    @JoinColumn(name = "articulo_id")
    @Builder.Default
    @NotAudited
    protected Set<ImagenArticulo> imagenes = new HashSet<>();



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_id")
    @JsonIgnoreProperties("articulos")
    protected Categoria categoria;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "articulo_alergenos",
            joinColumns = @JoinColumn(name = "articulo_id"),
            inverseJoinColumns = @JoinColumn(name = "alergenos_id"))
    @Builder.Default
    protected Set<Alergeno> alergenos=new HashSet<>();
}

