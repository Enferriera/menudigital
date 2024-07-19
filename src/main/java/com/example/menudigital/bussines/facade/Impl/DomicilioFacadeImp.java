package com.example.menudigital.bussines.facade.Impl;


import com.example.menudigital.bussines.facade.Base.BaseFacadeImp;
import com.example.menudigital.bussines.facade.DomicilioFacade;
import com.example.menudigital.bussines.mapper.BaseMapper;
import com.example.menudigital.bussines.services.base.BaseService;
import com.example.menudigital.domain.dtos.domicilioDto.DomicilioDto;
import com.example.menudigital.domain.entities.Domicilio;
import org.springframework.stereotype.Service;

@Service
public class DomicilioFacadeImp extends BaseFacadeImp<Domicilio, DomicilioDto, Long> implements DomicilioFacade {
    public DomicilioFacadeImp(BaseService<Domicilio, Long> baseService, BaseMapper<Domicilio, DomicilioDto> baseMapper) {
        super(baseService, baseMapper);
    }

}
