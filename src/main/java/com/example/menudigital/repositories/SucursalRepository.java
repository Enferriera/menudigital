package com.example.menudigital.repositories;

import com.example.menudigital.domain.entities.Categoria;
import com.example.menudigital.domain.entities.Sucursal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SucursalRepository extends BaseRepository<Sucursal, Long> {


    @Query("SELECT c FROM Sucursal s JOIN s.categorias c LEFT JOIN FETCH c.subCategorias WHERE s.id = :sucursalId AND c.eliminado=false  ")
    List<Categoria> findCategoriasBySucursalId(@Param("sucursalId") Long sucursalId);

    @Query("SELECT CASE WHEN COUNT(s.id) > 0 THEN TRUE ELSE FALSE END FROM Sucursal s WHERE s.empresa.id = :empresaId AND s.esCasaMatriz = TRUE AND s.eliminado=false")
    boolean existsSucursalByEsCasaMatriz(@Param("empresaId") Long empresaId);


    @Query("SELECT s FROM Sucursal s  WHERE s.empresa.id = :id AND s.eliminado=false")
    List<Sucursal> findAllByEmpresaId(@Param("id") Long id);
}
