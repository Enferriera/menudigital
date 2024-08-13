package com.example.menudigital.repositories;



import com.example.menudigital.domain.entities.Articulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloRepository extends BaseRepository<Articulo, Long> {

    @Query("SELECT CASE WHEN COUNT(a.id) > 0 THEN TRUE ELSE FALSE END FROM Articulo a JOIN a.alergenos al WHERE al.id = :idAlergeno AND a.eliminado=false")
    boolean existsArticuloByAlergeno(@Param("idAlergeno") Long idAlergeno);

    @Query("SELECT a FROM Articulo a JOIN a.categoria c JOIN c.sucursales s WHERE s.id = :idSucursal AND a.eliminado = false AND (c.eliminado = false)")
    List<Articulo> findAllBySucursalId(@Param("idSucursal") Long idSucusal);

    @Query("SELECT a FROM Articulo a " +
            "JOIN a.categoria c " +
            "JOIN c.sucursales s " +
            "WHERE s.id = :idSucursal " +
            "AND a.eliminado = false " +
            "AND c.eliminado = false")
    Page<Articulo> findAllBySucursalIdPaged(@Param("idSucursal") Long idSucursal, Pageable pageable);


    boolean existsByCodigo(String nombre);

    Articulo findByCodigoAndEliminadoFalse(String codigo);

    @Query("SELECT a FROM Articulo a JOIN a.categoria c WHERE c.id = :idCategoria AND a.eliminado = false AND c.eliminado = false")
    List<Articulo> findAllByCategoriaId(@Param("idCategoria") Long idCategoria);

    @Query("SELECT a FROM Articulo a JOIN a.categoria c WHERE c.id = :idCategoria AND a.eliminado = false AND c.eliminado = false")
    Page<Articulo> findAllByCategoriaIdPaged(@Param("idCategoria") Long idCategoria, Pageable pageable);

    @Query("SELECT a FROM Articulo a JOIN a.categoria c JOIN c.sucursales s WHERE s.id=:sucursalId AND a.eliminado=false AND a.habilitado=true")
    List<Articulo> findAllHabilitadoBySucursalId(@Param("sucursalId") Long sucursalId);

    }