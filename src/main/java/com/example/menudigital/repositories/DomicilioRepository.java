package com.example.menudigital.repositories;


import com.example.menudigital.domain.entities.Domicilio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DomicilioRepository extends BaseRepository<Domicilio,Long>{


    @Query("SELECT s.domicilio FROM Sucursal s WHERE s.id = :idSucursal")
    Domicilio findDomicilioBySucursalId(@Param("idSucursal") Long idSucursal);
}
