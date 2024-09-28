package com.example.menudigital.bussines.services.Impl;

import com.example.menudigital.bussines.services.EmpresaService;
import com.example.menudigital.bussines.services.ImageService;
import com.example.menudigital.bussines.services.SucursalService;
import com.example.menudigital.bussines.services.base.BaseServiceImp;
import com.example.menudigital.domain.entities.Empresa;
import com.example.menudigital.repositories.EmpresaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class EmpresaServiceImpl extends BaseServiceImp<Empresa,Long> implements EmpresaService {

    @Autowired
    SucursalService sucursalService;

    @Autowired
    EmpresaRepository empresaRepository;





    @Override
    @Transactional
    public Empresa addSucursal(Long idEmpresa, Long idSucursal) {
        Empresa empresa = empresaRepository.findWithSucursalesById(idEmpresa);
        empresa.getSucursales().add(sucursalService.getById(idSucursal));
        return empresa;


    }

    @Override
    public Empresa findWithSucursalesById(Long id) {
        return empresaRepository.findWithSucursalesById(id);
    }

    @Override
    @Transactional
    public Empresa create(Empresa request){
        var empresa = empresaRepository.findByCuit(request.getCuit());
        if(!empresa.isEmpty()){
            throw new RuntimeException("Ya existe una empresa con el nummero de CUIT: "+request.getCuit());
        }
        return empresaRepository.save(request);
    }

    @Override
    @Transactional
    public void deleteById(Long id){
        var empresa = empresaRepository.getById(id);
        System.out.println(empresa.getSucursales().size());
        if(empresa.getSucursales().size() > 0) {
            throw new RuntimeException("No se puede eliminar la empresa con el id: " + id + " porque tiene sucursales asociadas");
        }
        empresaRepository.delete(empresa);
    }

    @Override
    @Transactional
    public Empresa update(Empresa request, Long id){
        var optionalEntity = empresaRepository.getById(id);
        var entityByCuit= empresaRepository.findByCuit(request.getCuit());
        if(entityByCuit.isPresent() && !entityByCuit.get().getId().equals(id)) {
            throw new RuntimeException("Ya existe una empresa con el nummero de CUIT: " + request.getCuit());
        }

        return empresaRepository.save(request);
    }

    @Override
    public Empresa getById(Long id) {
        Empresa empresa = empresaRepository.getById(id);
        if (empresa==null) {
            throw new RuntimeException("No se existe la empresa con el id: " + id );
        }
        return empresaRepository.getById(id);
    }
}
