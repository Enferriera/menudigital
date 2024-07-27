package com.example.menudigital.bussines.facade.Impl;

import com.example.menudigital.bussines.facade.AlergenoFacade;
import com.example.menudigital.bussines.facade.Base.BaseFacadeImp;
import com.example.menudigital.bussines.mapper.BaseMapper;
import com.example.menudigital.bussines.services.base.BaseService;
import com.example.menudigital.domain.dtos.alergeno.AlergenoDto;
import com.example.menudigital.domain.entities.Alergeno;
import org.springframework.stereotype.Service;

@Service
public class AlergenoFacadeImpl extends BaseFacadeImp<Alergeno, AlergenoDto,Long> implements AlergenoFacade {
    public AlergenoFacadeImpl(BaseService<Alergeno, Long> baseService, BaseMapper<Alergeno, AlergenoDto> baseMapper) {
        super(baseService, baseMapper);
    }
}
