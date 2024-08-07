package com.example.menudigital.repositories;


import com.example.menudigital.domain.entities.Categoria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CategoriaRepository extends BaseRepository<Categoria,Long> {
    @Query("SELECT DISTINCT c FROM Categoria c " +
            "JOIN c.sucursales s " +
            "LEFT JOIN FETCH c.subCategorias sc " +
            "LEFT JOIN sc.sucursales s2 " +
            "WHERE s.id = :idSucursal AND c.eliminado = false " +
            "AND (sc IS NULL OR (sc.eliminado = false AND s2.id = :idSucursal)) " +
            "AND c.categoriaPadre IS NULL")
    List<Categoria> findAllCategoriasPadreBySucursalId(@Param("idSucursal") Long idSucursal);
    @Query("SELECT DISTINCT  c FROM Categoria c JOIN c.sucursales s WHERE s.empresa.id = :idEmpresa AND c.eliminado=false")
    List<Categoria> findAllCategoriasByEmpresaId(@Param("idEmpresa") Long idEmpresa);

    @Override
    @Query("SELECT c FROM Categoria c LEFT JOIN FETCH c.subCategorias sc  WHERE  c.eliminado=false AND (sc.eliminado=false OR sc IS NULL) AND c.id = :id")
    Categoria getById(@Param("id") Long id);

    @Query("SELECT c FROM Categoria c  WHERE  c.eliminado=false  AND c.categoriaPadre.id=:id")
    List<Categoria> findAllSubCategoriasByCategoriaPadreId(@Param("id") Long id);

    @Query("SELECT c FROM Categoria c JOIN c.sucursales s WHERE s.id = :idSucursal AND c.eliminado=false")
    List<Categoria> findAllCategoriasBySucursalId(@Param("idSucursal") Long idSucursal);
}

