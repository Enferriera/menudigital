package com.example.menudigital.repositories;


import com.example.menudigital.domain.entities.Provincia;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinciaRepository extends BaseRepository<Provincia,Long>{

    List<Provincia> findByPaisId(Long id);
}
