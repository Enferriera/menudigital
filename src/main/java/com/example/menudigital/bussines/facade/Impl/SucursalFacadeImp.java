package com.example.menudigital.bussines.facade.Impl;

import com.example.menudigital.bussines.facade.Base.BaseFacadeImp;
import com.example.menudigital.bussines.facade.SucursalFacade;
import com.example.menudigital.bussines.mapper.BaseMapper;
import com.example.menudigital.bussines.mapper.CategoriaMapper;
import com.example.menudigital.bussines.services.SucursalService;
import com.example.menudigital.bussines.services.base.BaseService;
import com.example.menudigital.bussines.services.base.BaseServiceImp;
import com.example.menudigital.domain.dtos.categoriaDto.CategoriaDto;
import com.example.menudigital.domain.dtos.sucursalDto.SucursalDto;
import com.example.menudigital.domain.entities.Sucursal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SucursalFacadeImp extends BaseFacadeImp<Sucursal, SucursalDto, Long> implements SucursalFacade {
    private static final Logger logger = LoggerFactory.getLogger(BaseServiceImp.class);
    @Autowired
    SucursalService sucursalService;

    @Autowired
    CategoriaMapper categoriaMapper;

    public SucursalFacadeImp(BaseService<Sucursal, Long> baseService, BaseMapper<Sucursal, SucursalDto> baseMapper) {
        super(baseService, baseMapper);
    }


    @Override
    public SucursalDto createSucursal(SucursalDto dto) {
        var sucursal=baseMapper.toEntity(dto);
        var sucursalPersistida=sucursalService.guardarSucursal(sucursal);
        return baseMapper.toDTO(sucursalPersistida);
    }

    @Override
    public SucursalDto updateSucursal(Long id, SucursalDto dto) {

        var sucursal=baseMapper.toEntity(dto);

        var sucursalActualizada=sucursalService.actualizarSucursal(id,sucursal);
        return baseMapper.toDTO(sucursalActualizada);
    }

    public List<CategoriaDto> findCategoriasBySucursalId(Long id) {
        // Busca una entidad por id
        var entities = sucursalService.findCategoriasBySucursalId(id);

        // convierte la entidad a DTO
        return entities
                .stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsSucursalByEsCasaMatriz(Long id){
        return sucursalService.existsSucursalByEsCasaMatriz(id);
    }

    @Override
    public List<SucursalDto> findAllByEmpresaId(Long id) {
        var entities = sucursalService.findAllByEmpresaId(id);
        return entities
                .stream()
                .map(baseMapper::toDTO)
                .collect(Collectors.toList());
    }
}
