package com.example.menudigital.bussines.facade.Impl;

import com.example.menudigital.bussines.facade.Base.BaseFacadeImp;
import com.example.menudigital.bussines.facade.CategoriaFacade;
import com.example.menudigital.bussines.mapper.BaseMapper;
import com.example.menudigital.bussines.mapper.CategoriaMapper;
import com.example.menudigital.bussines.services.CategoriaService;
import com.example.menudigital.bussines.services.base.BaseService;
import com.example.menudigital.domain.dtos.categoriaDto.CategoriaCreateDto;
import com.example.menudigital.domain.dtos.categoriaDto.CategoriaDto;
import com.example.menudigital.domain.dtos.domicilioDto.DomicilioDto;
import com.example.menudigital.domain.entities.Categoria;
import com.example.menudigital.domain.entities.Domicilio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaFacadeImpl extends BaseFacadeImp<Categoria, CategoriaDto, Long> implements CategoriaFacade {
    public CategoriaFacadeImpl(BaseService<Categoria, Long> baseService, BaseMapper<Categoria, CategoriaDto> baseMapper) {
        super(baseService, baseMapper);
    }
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaMapper categoriaMapper;

    @Override
    public List<CategoriaDto> findAllCategoriasBySucursalId(Long idSucursal) {
        return baseMapper.toDTOsList(categoriaService.findAllCategoriasBySucursalId(idSucursal));
    }

    @Override
    @Transactional
    public CategoriaDto createCategoria(CategoriaCreateDto dto){
        return baseMapper.toDTO(categoriaService.create(categoriaMapper.toEntityCreate(dto)));
    }

    @Override
    @Transactional
    public void deleteCategoriaInSucursales(Long idCategoria, Long idSucursal) {
       categoriaService.deleteCategoriaInSucursales(idCategoria, idSucursal);
    }


    @Override
    public List<CategoriaDto> findAllCategoriasByEmpresaId( Long idEmpresa){
        return baseMapper.toDTOsList(categoriaService.findAllCategoriasByEmpresaId(idEmpresa));
    }

    @Override
    @Transactional
    public CategoriaDto updateCategoria(Long id, CategoriaCreateDto dto){
        var categoria = categoriaMapper.toEntityCreate(dto);
        return categoriaMapper.toDTO(categoriaService.update(categoria,id));
    }

}
