package com.example.menudigital.bussines.services.Impl;

import com.example.menudigital.bussines.services.SucursalService;
import com.example.menudigital.bussines.services.base.BaseServiceImp;
import com.example.menudigital.domain.entities.Categoria;
import com.example.menudigital.domain.entities.Sucursal;
import com.example.menudigital.repositories.CategoriaRepository;
import com.example.menudigital.repositories.DomicilioRepository;
import com.example.menudigital.repositories.EmpresaRepository;
import com.example.menudigital.repositories.SucursalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SucursalServiceImpl extends BaseServiceImp<Sucursal, Long> implements SucursalService {
    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    private DomicilioRepository domicilioRepository;
    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;


    @Override
    @Transactional
    public Sucursal create(Sucursal sucursal) {
        var empresa = empresaRepository.getById(sucursal.getEmpresa().getId());
       var domicilio=domicilioRepository.save(sucursal.getDomicilio());
        domicilioRepository.save(domicilio);
        sucursal.setDomicilio(domicilio);

        List<Categoria> categorias = categoriaRepository.findAllCategoriasByEmpresaId(sucursal.getEmpresa().getId());

        var sucursalPersisted = sucursalRepository.save(sucursal);
        //Asociamos las categorias de la empresa a la nueva sucursal
        for(Categoria categoria: categorias) {
            categoria.getSucursales().add(sucursalPersisted);
            categoriaRepository.save(categoria);
            sucursalPersisted.getCategorias().add(categoria);
        }
        empresa.getSucursales().add(sucursalPersisted);
        sucursalRepository.save(sucursal);
        empresaRepository.save(empresa);
        return sucursalPersisted;

    }

    @Override
    @Transactional
    public Sucursal update(Sucursal sucursal, Long id) {
        var sucursalActualizar = sucursalRepository.getById(sucursal.getId());
        var domicilio = domicilioRepository.getById(sucursal.getDomicilio().getId());
        domicilioRepository.save(sucursal.getDomicilio());
        var empresa = empresaRepository.getById(sucursal.getEmpresa().getId());
        sucursal.setDomicilio(domicilio);
        sucursal.setEmpresa(empresa);
        sucursal.setCategorias(sucursalActualizar.getCategorias());
        return sucursalRepository.save(sucursal);
    }

    @Override
    public List<Categoria> findCategoriasBySucursalId(Long sucursalId) {
        var sucursalExiste = sucursalRepository.getById(sucursalId);

        List<Categoria> categorias = sucursalRepository.findCategoriasBySucursalId(sucursalId);

        return categorias;
    }


    @Override
    public boolean existsSucursalByEsCasaMatriz(Long id) {
        return sucursalRepository.existsSucursalByEsCasaMatriz(id);
    }

    @Override
    public List<Sucursal> findAllByEmpresaId(Long id) {
        return sucursalRepository.findAllByEmpresaId(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        var sucursal = sucursalRepository.getById(id);
        if(sucursal.getCategorias().size() > 0) {
            throw new RuntimeException("No se puede eliminar la sucursal porque tiene categorias asociadas");
        }
        sucursal.setEmpresa(null);
        sucursalRepository.save(sucursal);
        sucursalRepository.delete(sucursal);
    }
}
