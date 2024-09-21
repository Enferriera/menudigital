package com.example.menudigital.bussines.facade.Impl;

import com.example.menudigital.bussines.facade.AlergenoFacade;
import com.example.menudigital.bussines.facade.Base.BaseFacadeImp;
import com.example.menudigital.bussines.mapper.BaseMapper;
import com.example.menudigital.bussines.services.AlergenoService;
import com.example.menudigital.bussines.services.base.BaseService;
import com.example.menudigital.domain.dtos.alergeno.AlergenoDto;
import com.example.menudigital.domain.entities.Alergeno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AlergenoFacadeImpl extends BaseFacadeImp<Alergeno, AlergenoDto,Long> implements AlergenoFacade {
    @Autowired
    private AlergenoService alergenoService;
    public AlergenoFacadeImpl(BaseService<Alergeno, Long> baseService, BaseMapper<Alergeno, AlergenoDto> baseMapper) {
        super(baseService, baseMapper);
    }

    @Override
    public ResponseEntity<String> deleteImage(String publicId, Long id) {
        return alergenoService.deleteImage(publicId, id);
    }
}
