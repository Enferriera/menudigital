package com.example.menudigital.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Setter
@SuperBuilder
@Audited
public class Alergeno extends Base {

    private String denominacion;
    @OneToOne(cascade = CascadeType.ALL)
    @NotAudited
    private ImagenAlergeno imagen;


}
