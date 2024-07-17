package com.example.menudigital.repositories;

import com.example.menudigital.domain.entities.Empresa;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends BaseRepository<Empresa, Long> {
}
