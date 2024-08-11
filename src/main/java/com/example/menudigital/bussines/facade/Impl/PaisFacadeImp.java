package com.example.menudigital.bussines.facade.Impl;


import com.example.menudigital.bussines.facade.Base.BaseFacadeImp;
import com.example.menudigital.bussines.facade.PaisFacade;
import com.example.menudigital.bussines.mapper.BaseMapper;
import com.example.menudigital.bussines.services.base.BaseService;
import com.example.menudigital.domain.dtos.paisDto.PaisDto;
import com.example.menudigital.domain.entities.Pais;
import org.springframework.stereotype.Service;


@Service
public class PaisFacadeImp extends BaseFacadeImp<Pais, PaisDto,Long> implements PaisFacade {
    public PaisFacadeImp(BaseService<Pais, Long> baseService, BaseMapper<Pais, PaisDto> baseMapper) {
        super(baseService, baseMapper);
    }
}
