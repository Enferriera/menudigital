package com.example.menudigital.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

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
public class Categoria extends Base{
    private String denominacion;



    @OneToMany(mappedBy = "categoria")
    //@JoinColumn(name = "categoria_id")
    @Builder.Default
    private Set<Articulo> articulos = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "categoriaPadre")
    @JsonIgnoreProperties("categoriaPadre")
    @Builder.Default
    private Set<Categoria> subCategorias = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "categoria_padre_id")
    @JsonIgnoreProperties("subCategorias")
    private Categoria categoriaPadre;

    @ManyToMany(mappedBy = "categorias")
    @Builder.Default
    private Set<Sucursal> sucursales = new HashSet<>();
}
