package com.example.menudigital.repositories;


import com.example.menudigital.domain.entities.Articulo;
import com.example.menudigital.domain.entities.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
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
/*
    @Override
    @Query(value = "SELECT c.*\n" +
            "FROM categoria c\n" +
            "LEFT JOIN  categoria sc ON sc.categoria_padre_id = c.id AND sc.eliminado = false\n" +
            "LEFT JOIN  articulo a ON a.categoria_id = c.id AND a.eliminado = false\n" +
            "WHERE c.id = ?\n" +
            "  AND c.eliminado = false\n" +
            "GROUP BY c.id", nativeQuery = true)
    Categoria getById(@Param("id") Long id);*/

    @Override
    @Query("SELECT c FROM Categoria c " +
            "LEFT JOIN FETCH c.subCategorias sc " +
            "LEFT JOIN FETCH c.articulos a " +
            "WHERE c.id = :id " +
            "AND c.eliminado = false " +
            "AND (sc.eliminado = false OR sc IS NULL) "
            )
    Categoria getById(@Param("id") Long id);


    @Query("SELECT c FROM Categoria c  WHERE  c.eliminado=false  AND c.categoriaPadre.id=:id")
    List<Categoria> findAllSubCategoriasByCategoriaPadreId(@Param("id") Long id);

    @Query("SELECT c FROM Categoria c JOIN c.sucursales s WHERE s.id = :idSucursal AND c.eliminado=false")
    List<Categoria> findAllCategoriasBySucursalId(@Param("idSucursal") Long idSucursal);

    @Query("SELECT c FROM Categoria c JOIN c.sucursales s WHERE s.id = :idSucursal AND c.eliminado=false AND c.categoriaPadre IS NOT NULL")
    List<Categoria> findSubcategoriasBySucursalId(@Param("idSucursal") Long idSucursal);
}

