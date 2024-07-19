package com.example.menudigital.bussines.services.Impl;

import com.example.menudigital.bussines.services.SucursalService;
import com.example.menudigital.bussines.services.base.BaseServiceImp;
import com.example.menudigital.domain.entities.Categoria;
import com.example.menudigital.domain.entities.Sucursal;
import com.example.menudigital.repositories.DomicilioRepository;
import com.example.menudigital.repositories.EmpresaRepository;
import com.example.menudigital.repositories.SucursalRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SucursalServiceImpl extends BaseServiceImp<Sucursal,Long> implements SucursalService {
    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    private DomicilioRepository domicilioRepository;
    @Autowired
    private EmpresaRepository empresaRepository;


    @Override
    @Transactional
    public Sucursal guardarSucursal(Sucursal sucursal) {
        var domicilio = sucursal.getDomicilio();
        if(domicilio.getId() != null){
            var domicilioBd = domicilioRepository.findById(domicilio.getId());
            domicilioBd.ifPresent(sucursal::setDomicilio);
        }else{
            domicilioRepository.save(domicilio);
            sucursal.setDomicilio(domicilio);
        }
        var empresa = empresaRepository.findById(sucursal.getEmpresa().getId());
        if(empresa.isEmpty()){
            throw new RuntimeException("Empresa no encontrada");
        }

        return sucursalRepository.save(sucursal);
    }

    @Override
    @Transactional
    public Sucursal actualizarSucursal(Long id,Sucursal sucursal) {
        var sucursalActualizar = sucursalRepository.findById(sucursal.getId());
        if(sucursalActualizar.isEmpty()){
            throw new RuntimeException("Sucursal no encontrada: { id: " + id + " }");
        }
        var domicilio = domicilioRepository.findById(sucursal.getDomicilio().getId());
        domicilioRepository.save(sucursal.getDomicilio());
        var empresa = empresaRepository.findById(sucursal.getEmpresa().getId());

        sucursal.setDomicilio(domicilio.get());
        sucursal.setEmpresa(empresa.get());
        return sucursalRepository.save(sucursal);
    }

    @Override
    public List<Categoria> findCategoriasBySucursalId(Long sucursalId) {
        var sucursalExiste = sucursalRepository.findById(sucursalId);

        if(sucursalExiste.isEmpty()){
            throw new RuntimeException("Sucursal no encontrada: { id: " + sucursalId + " }");
        }

        List<Categoria> categorias = sucursalRepository.findCategoriasBySucursalId(sucursalId);
        Set<Categoria> filteredCategorias = new HashSet<>();
        for (Categoria categoria: categorias){
            if (categoria.getCategoriaPadre() == null) {
                filteredCategorias.add(categoria);
            }
        }
        filterSubcategorias(filteredCategorias, categorias);

        return new ArrayList<>(filteredCategorias);
    }

    public void filterSubcategorias(Set<Categoria> categorias, List<Categoria> categoriasBySucursal) {
        for (Categoria categoria : categorias) {
            System.out.println(categoria.getDenominacion());
            Set<Categoria> subcategorias = categoria.getSubCategorias();
            if (!subcategorias.isEmpty()) {
                Set<Categoria> filteredSubcategorias = new HashSet<>();
                for (Categoria subcategoria: subcategorias){
                    System.out.println(subcategoria.getDenominacion());
                    if (categoriasBySucursal.contains(subcategoria)) {
                        System.out.println("está en sucursal");
                        filteredSubcategorias.add(subcategoria);
                    }
                }
                categoria.setSubCategorias(filteredSubcategorias);
                filterSubcategorias(filteredSubcategorias, categoriasBySucursal);
            }

        }
    }

    @Override
    public boolean existsSucursalByEsCasaMatriz(Long id) {
        return sucursalRepository.existsSucursalByEsCasaMatriz(id);
    }

    @Override
    public List<Sucursal> findAllByEmpresaId(Long id) {
        return sucursalRepository.findAllByEmpresaId(id);
    }
}
