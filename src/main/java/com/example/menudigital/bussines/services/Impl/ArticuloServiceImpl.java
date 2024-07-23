package com.example.menudigital.bussines.services.Impl;

import com.example.menudigital.bussines.services.ArticuloService;
import com.example.menudigital.bussines.services.base.BaseServiceImp;
import com.example.menudigital.domain.entities.Articulo;
import com.example.menudigital.repositories.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticuloServiceImpl extends BaseServiceImp<Articulo,Long> implements ArticuloService {

    @Autowired
    private ArticuloRepository articuloRepository;

    @Override
    public boolean existsArticuloByAlergeno(Long idAlergeno){
        return articuloRepository.existsArticuloByAlergeno(idAlergeno);
    }

    @Override
   public List<Articulo> findAllBySucursalId(Long idSucusal){
        return articuloRepository.findAllBySucursalId(idSucusal);
    }
}
