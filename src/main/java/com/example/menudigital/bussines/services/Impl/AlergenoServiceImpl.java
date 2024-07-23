package com.example.menudigital.bussines.services.Impl;

import com.example.menudigital.bussines.services.AlergenoService;
import com.example.menudigital.bussines.services.ArticuloService;
import com.example.menudigital.bussines.services.base.BaseServiceImp;
import com.example.menudigital.domain.entities.Alergeno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlergenoServiceImpl extends BaseServiceImp<Alergeno,Long> implements AlergenoService {

    @Autowired
    private ArticuloService articuloService;

    @Override
    public void deleteById(Long id) {
        if (articuloService.existsArticuloByAlergeno(id)) {
            throw new RuntimeException("No se puede eliminar el alergeno porque tiene articulos asociados");
        }
        super.deleteById(id);
    }
}
