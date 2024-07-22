package com.example.menudigital.bussines.services.Impl;

import com.example.menudigital.bussines.services.CategoriaService;
import com.example.menudigital.bussines.services.SucursalService;
import com.example.menudigital.bussines.services.base.BaseServiceImp;
import com.example.menudigital.domain.entities.Categoria;
import com.example.menudigital.domain.entities.Sucursal;
import com.example.menudigital.repositories.CategoriaRepository;
import com.example.menudigital.repositories.SucursalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl extends BaseServiceImp<Categoria,Long> implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private SucursalService sucursalService;

    @Override
    public List<Categoria> findAllCategoriasBySucursalId(Long idSucursal){
        return categoriaRepository.findAllCategoriasBySucursalId(idSucursal);
    }

    @Override
    @Transactional
    public Categoria create(Categoria categoria) {
        Set<Sucursal> sucursales = new HashSet<>();

        // Verificar y asociar sucursales existentes
        if (categoria.getSucursales() != null && !categoria.getSucursales().isEmpty()) {
            for (Sucursal sucursal : categoria.getSucursales()) {
                Sucursal sucursalBd = sucursalService.getById(sucursal.getId());
                sucursalBd.getCategorias().add(categoria);
                sucursales.add(sucursalBd);
            }
        }

        // Establecer la nueva colección de sucursales en la categoría
        categoria.setSucursales(sucursales);

        // Mapear subcategorías y guardar la categoría

        if(categoria.getCategoriaPadre()!= null){
            Categoria categoriaPadre = categoriaRepository.getById(categoria.getCategoriaPadre().getId());

            categoria.setCategoriaPadre(categoriaPadre);
            var categoriaHija=categoriaRepository.save(categoria);
            categoriaPadre.getSubCategorias().add(categoria);
            categoriaRepository.save(categoriaPadre);
            return categoriaHija;
        }
        return categoriaRepository.save(categoria);
    }

    @Override
    @Transactional
    public void deleteCategoriaInSucursales(Long idCategoria, Long idSucursal) {
        Categoria categoriaExistente = categoriaRepository.getById(idCategoria);


        Sucursal sucursal = sucursalService.getById(idSucursal);

        // Eliminar la relación entre la sucursal y la categoría existente
        sucursal.getCategorias().remove(categoriaExistente);
        categoriaExistente.getSucursales().remove(sucursal);

        categoriaRepository.save(categoriaExistente);
    }

    @Override
   public List<Categoria> findAllCategoriasByEmpresaId( Long idEmpresa){
        return categoriaRepository.findAllCategoriasByEmpresaId(idEmpresa);
    }


    @Override
    @Transactional
    public Categoria update(Categoria newCategoria, Long id) {
        Categoria categoriaExistente = categoriaRepository.getById(id);


        // Actualizar los detalles básicos de la categoría
        categoriaExistente.setDenominacion(newCategoria.getDenominacion());


        // Actualizar las sucursales asociadas
        Set<Sucursal> newSucursales = newCategoria.getSucursales().stream()
                .map(sucursal -> sucursalService.getById(sucursal.getId()))
                .collect(Collectors.toSet());

        Set<Sucursal> existingSucursales = categoriaExistente.getSucursales();

        // Remover asociaciones obsoletas
        existingSucursales.removeIf(sucursal -> {
            boolean remove = !newSucursales.contains(sucursal);
            if (remove) {
                sucursal.getCategorias().remove(categoriaExistente);
                sucursalService.update(sucursal, sucursal.getId());
            }
            return remove;
        });

        // Agregar nuevas asociaciones
        for (Sucursal sucursal : newSucursales) {
            if (!existingSucursales.contains(sucursal)) {
                existingSucursales.add(sucursal);
                sucursal.getCategorias().add(categoriaExistente);
                sucursalService.update(sucursal, sucursal.getId());
            }
        }

        // Actualizar la relación de sucursales de la categoría existente
        categoriaExistente.setSucursales(existingSucursales);

        // Manejar subcategorías
        actualizarSubcategorias(categoriaExistente, newCategoria, newSucursales);
        categoriaExistente.setCategoriaPadre(newCategoria.getCategoriaPadre());

        return categoriaRepository.save(categoriaExistente);
    }


    private void actualizarSubcategorias(Categoria categoriaExistente, Categoria newCategoria, Set<Sucursal> sucursales){
        if (!newCategoria.getSubCategorias().isEmpty()){
            for(Categoria subcategoriaNueva: newCategoria.getSubCategorias()){
                Optional<Categoria> subcategoriaExistenteOpt = categoriaExistente.getSubCategorias().stream()
                        .filter(sc -> sc.getId().equals(subcategoriaNueva.getId()))
                        .findFirst();

                if (subcategoriaExistenteOpt.isPresent()) {
                    Categoria subcategoriaExistente = subcategoriaExistenteOpt.get();
                    subcategoriaExistente.setDenominacion(subcategoriaNueva.getDenominacion());
                    subcategoriaExistente.setSucursales(sucursales);
                    for (Sucursal sucursal : sucursales) {
                        boolean categoriaExists = sucursal.getCategorias().stream()
                                .anyMatch(cat -> cat.getId() != null && cat.getId().equals(subcategoriaExistente.getId()));

                        if (!categoriaExists) {
                            sucursal.getCategorias().add(subcategoriaExistente);
                        }
                    }
                    actualizarSubcategorias(subcategoriaExistente, subcategoriaNueva, sucursales);
                } else {
                    subcategoriaNueva.setCategoriaPadre(categoriaExistente);
                    subcategoriaNueva.setSucursales(sucursales);
                    categoriaExistente.getSubCategorias().add(subcategoriaNueva);

                    for (Sucursal sucursal : sucursales) {
                        sucursal.getCategorias().add(subcategoriaNueva);
                    }
                    actualizarSubcategorias(subcategoriaNueva, subcategoriaNueva, sucursales);

                }
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        var categoria = categoriaRepository.getById(id);
        if(!categoria.getArticulos().isEmpty() || !categoria.getSubCategorias().isEmpty()) {
            throw new RuntimeException("No se puede eliminar la categoría, porque tiene artículos o subcategorías asociadas");
        }
        for(Sucursal sucursal : categoria.getSucursales()) {
            sucursal.getCategorias().remove(categoria);
            sucursalService.update(sucursal, sucursal.getId());
        }
        categoriaRepository.delete(categoria);
    }
}
