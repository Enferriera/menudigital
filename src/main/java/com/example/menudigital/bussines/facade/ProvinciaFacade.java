package com.example.menudigital.bussines.facade;


import com.example.menudigital.bussines.facade.Base.BaseFacade;
import com.example.menudigital.domain.dtos.domicilioDto.ProvinciaDto;

import java.util.List;

public interface ProvinciaFacade extends BaseFacade<ProvinciaDto, Long> {
    List<ProvinciaDto> findByPaisId(Long id);
}
