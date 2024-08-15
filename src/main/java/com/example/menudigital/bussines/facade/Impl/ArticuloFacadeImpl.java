package com.example.menudigital.bussines.facade.Impl;

import com.example.menudigital.bussines.facade.ArticuloFacade;
import com.example.menudigital.bussines.facade.Base.BaseFacadeImp;
import com.example.menudigital.bussines.mapper.ArticuloMapper;
import com.example.menudigital.bussines.mapper.BaseMapper;
import com.example.menudigital.bussines.services.ArticuloService;
import com.example.menudigital.bussines.services.base.BaseService;
import com.example.menudigital.domain.dtos.articuloDto.ArticuloCreateDto;
import com.example.menudigital.domain.dtos.articuloDto.ArticuloDto;
import com.example.menudigital.domain.entities.Articulo;
import com.example.menudigital.repositories.ArticuloRepository;
import com.example.menudigital.utils.config.DbCacheConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticuloFacadeImpl extends BaseFacadeImp<Articulo, ArticuloDto,Long> implements ArticuloFacade {
    @Autowired
    private ArticuloRepository articuloRepository;

    public ArticuloFacadeImpl(BaseService<Articulo, Long> baseService, BaseMapper<Articulo, ArticuloDto> baseMapper) {
        super(baseService, baseMapper);
    }

    @Autowired
    private ArticuloMapper articuloMapper;

    @Autowired
    private ArticuloService articuloService;

    @Override
    @Cacheable(value = DbCacheConfig.CACHE_NAME)
    public List<ArticuloDto> findAllBySucursalId(Long idSucusal){
        return articuloMapper.toDTOsList(articuloService.findAllBySucursalId(idSucusal));
    }

    @Override
    public ArticuloDto createArticulo(ArticuloCreateDto dto){
        return articuloMapper.toDTO(articuloService.create(articuloMapper.toEntityCreate(dto)));
    }

    @Override
    public ArticuloDto updateArticulo(ArticuloCreateDto dto,Long id){
        // Convierte a entidad
        var entityToUpdate = articuloMapper.toEntityCreate(dto);
        // Graba una entidad
        var entityUpdated = baseService.update(entityToUpdate,id);
        // convierte a Dto para devolver
        return baseMapper.toDTO(entityUpdated);
    }

    @Override
    public Page<ArticuloDto> findAllBySucursalIdPaged(Long idSucursal, Pageable pageable){
        return articuloMapper.toDTOsPage(articuloService.findAllBySucursalIdPaged(idSucursal, pageable));
    }

    @Override
    public List<ArticuloDto> findAllByCategoriaId(Long idCategoria) {
        return articuloMapper.toDTOsList(articuloService.findAllByCategoriaId(idCategoria));
    }

    @Override
    public Page<ArticuloDto> findAllByCategoriaIdPaged(Long idCategoria, Pageable pageable) {
        return articuloMapper.toDTOsPage(articuloService.findAllByCategoriaIdPaged(idCategoria, pageable));
    }

    @Override
    public List<ArticuloDto> findAllHabilitadoBySucursalId(Long sucursalId){
        return articuloMapper.toDTOsList(articuloService.findAllHabilitadoBySucursalId(sucursalId));
    }

    @Override
    public boolean cambiarHabilitado(Long idArticulo){
        return articuloService.cambiarHabilitado(idArticulo);
    }
}
