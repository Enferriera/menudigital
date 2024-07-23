package com.example.menudigital.repositories;



import com.example.menudigital.domain.entities.Articulo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloRepository extends BaseRepository<Articulo, Long> {

    @Query("SELECT CASE WHEN COUNT(a.id) > 0 THEN TRUE ELSE FALSE END FROM Articulo a JOIN a.alergenos al WHERE al.id = :idAlergeno AND a.eliminado=false")
    boolean existsArticuloByAlergeno(@Param("idAlergeno") Long idAlergeno);

    @Query("SELECT a FROM Articulo a  JOIN  a.categoria c JOIN c.sucursales s WHERE s.id = :idSucursal AND a.eliminado=false AND s.eliminado=false")
    List<Articulo> findAllBySucursalId(@Param("idSucursal") Long idSucusal);

    boolean existsByCodigo(String nombre);

    Articulo findByCodigoAndEliminadoFalse(String codigo);
}
