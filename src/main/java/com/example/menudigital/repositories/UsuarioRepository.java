package com.example.menudigital.repositories;


import com.example.menudigital.domain.entities.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {
}
