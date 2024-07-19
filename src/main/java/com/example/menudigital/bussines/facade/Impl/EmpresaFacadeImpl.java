package com.example.menudigital.bussines.facade.Impl;


import com.example.menudigital.bussines.facade.Base.BaseFacadeImp;
import com.example.menudigital.bussines.facade.EmpresaFacade;
import com.example.menudigital.bussines.mapper.BaseMapper;
import com.example.menudigital.bussines.mapper.EmpresaMapper;
import com.example.menudigital.bussines.services.EmpresaService;
import com.example.menudigital.bussines.services.base.BaseService;
import com.example.menudigital.domain.dtos.empresaDto.EmpresaDto;
import com.example.menudigital.domain.dtos.empresaDto.EmpresaLargeDto;
import com.example.menudigital.domain.entities.Empresa;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaFacadeImpl extends BaseFacadeImp<Empresa, EmpresaDto,Long> implements EmpresaFacade {

    public EmpresaFacadeImpl(BaseService<Empresa, Long> baseService, BaseMapper<Empresa, EmpresaDto> baseMapper) {
        super(baseService, baseMapper);
    }

    @Autowired
    EmpresaMapper empresaMapper;


    @Autowired
    EmpresaService empresaService;
    @Override
    public EmpresaLargeDto addSucursal(Long idEmpresa, Long idSucursal) {
        return empresaMapper.toLargeDto(empresaService.addSucursal(idEmpresa, idSucursal));
    }


    @Override
    @Transactional
    public EmpresaLargeDto findWithSucursalesById(Long id) {
        return empresaMapper.toLargeDto(empresaService.findWithSucursalesById(id));
    }



}
