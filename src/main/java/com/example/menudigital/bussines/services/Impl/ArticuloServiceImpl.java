package com.example.menudigital.bussines.services.Impl;

import com.example.menudigital.bussines.services.ArticuloService;
import com.example.menudigital.bussines.services.base.BaseServiceImp;
import com.example.menudigital.domain.entities.Articulo;
import com.example.menudigital.repositories.ArticuloRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticuloServiceImpl extends BaseServiceImp<Articulo,Long> implements ArticuloService {

    @Autowired
    private ArticuloRepository articuloRepository;

    @Override
    public boolean existsArticuloByAlergeno(Long idAlergeno){
        return articuloRepository.existsArticuloByAlergeno(idAlergeno);
    }

    @Override
   public List<Articulo> findAllBySucursalId(Long idSucusal){
        return articuloRepository.findAllBySucursalId(idSucusal);
    }

    @Override
    @Transactional
    public Articulo create(Articulo newArticulo){
        if(articuloRepository.existsByCodigo(newArticulo.getCodigo())){
            throw new RuntimeException("Ya existe un articulo con el codigo "+newArticulo.getCodigo());
        }
        return articuloRepository.save(newArticulo);
    }

    @Override
    @Transactional
    public Articulo update(Articulo articulo, Long id) {
        var optionalEntity = baseRepository.getById(id);
        var articuloPorCodigo = articuloRepository.findByCodigoAndEliminadoFalse(articulo.getCodigo());

        if (articuloPorCodigo != null && articuloPorCodigo.getId() != id) {
            throw new RuntimeException("Ya existe un articulo con el codigo " + articulo.getCodigo());
        }
        return articuloRepository.save(articulo);
    }

    @Override
    public Page<Articulo> findAllBySucursalIdPaged(Long idSucursal, Pageable pageable){
        return articuloRepository.findAllBySucursalIdPaged(idSucursal,pageable);
    }


    @Override
    public List<Articulo> findAllByCategoriaId(Long idCategoria){
        return articuloRepository.findAllByCategoriaId(idCategoria);
    }

    @Override
    public Page<Articulo> findAllByCategoriaIdPaged(Long idCategoria, Pageable pageable){
        return articuloRepository.findAllByCategoriaIdPaged(idCategoria, pageable);
    }

    @Override
    public List<Articulo> findAllHabilitadoBySucursalId(Long sucursalId){
        return articuloRepository.findAllHabilitadoBySucursalId(sucursalId);
    }

    @Override
    @Transactional
    public boolean cambiarHabilitado(Long idArticulo){
        var articuloPersisted=articuloRepository.getById(idArticulo);
        articuloPersisted.setHabilitado(!articuloPersisted.isHabilitado());
        articuloRepository.save(articuloPersisted);
        return true;
    }

}
