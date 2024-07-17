package com.example.menudigital.repositories;


import com.example.menudigital.domain.entities.Localidad;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalidadRepository extends BaseRepository<Localidad,Long>{
    List<Localidad> findByProvinciaId(Long id);
}
