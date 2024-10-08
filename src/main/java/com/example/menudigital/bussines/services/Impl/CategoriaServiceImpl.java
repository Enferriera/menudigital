package com.example.menudigital.bussines.services.Impl;

import com.example.menudigital.bussines.services.CategoriaService;
import com.example.menudigital.bussines.services.SucursalService;
import com.example.menudigital.bussines.services.base.BaseServiceImp;
import com.example.menudigital.domain.entities.Articulo;
import com.example.menudigital.domain.entities.Categoria;
import com.example.menudigital.domain.entities.Empresa;
import com.example.menudigital.domain.entities.Sucursal;
import com.example.menudigital.repositories.CategoriaRepository;
import com.example.menudigital.repositories.SucursalRepository;
import jakarta.transaction.Transactional;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
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
    @Autowired
    private SucursalRepository sucursalRepository;

    @Override
    public List<Categoria> findAllCategoriasPadreBySucursalId(Long idSucursal){
        var categorias= categoriaRepository.findAllCategoriasPadreBySucursalId(idSucursal);
        categorias.forEach(cat->cat.setSubCategorias(getSubcategoriaInternosNoEliminados(cat.getSubCategorias())));
        return categorias;
    }

@Override
@Transactional
public Categoria create(Categoria categoria) {
    System.out.println("INGRESE AL SERVICIO");
    Set<Sucursal> sucursales = new HashSet<>();

    // Verificar y asociar sucursales existentes
    if (categoria.getSucursales() != null && !categoria.getSucursales().isEmpty()) {
        for (Sucursal sucursal : categoria.getSucursales()) {
            Sucursal sucursalBd = sucursalService.getById(sucursal.getId());
            sucursales.add(sucursalBd);
        }
    }

    // Establecer la nueva colección de sucursales en la categoría
    categoria.setSucursales(sucursales);

    // Guardar la categoría
    Categoria savedCategoria = categoriaRepository.save(categoria);

    // Actualizar la relación bidireccional para sucursales
    for (Sucursal sucursal : sucursales) {
        sucursal.getCategorias().add(savedCategoria);
        sucursalService.update(sucursal,sucursal.getId()); // Asegúrate de tener un método save en sucursalService
    }

    // Mapear subcategorías y actualizar la categoría padre si existe
    if (categoria.getCategoriaPadre() != null) {
        Categoria categoriaPadre = categoriaRepository.getById(categoria.getCategoriaPadre().getId());
        savedCategoria.setCategoriaPadre(categoriaPadre);
        categoriaRepository.save(savedCategoria);

        categoriaPadre.getSubCategorias().add(savedCategoria);
        categoriaRepository.save(categoriaPadre);
    }

    return savedCategoria;
}
    @Override
    @Transactional
    public void deleteCategoriaInSucursales(Long idCategoria, Long idSucursal) {
        Categoria categoriaExistente = categoriaRepository.getById(idCategoria);

        System.out.println(categoriaExistente.getId());
        Sucursal sucursal = sucursalService.getById(idSucursal);

        // Eliminar la relación entre la sucursal y la categoría existente
        sucursal.getCategorias().remove(categoriaExistente);
        sucursalRepository.save(sucursal);
        categoriaExistente.getSucursales().remove(sucursal);
        categoriaRepository.save(categoriaExistente);
        if(categoriaExistente.getSucursales().size()==0){
            categoriaRepository.delete(categoriaExistente);
        }
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
    @Transactional
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

    @Override
    public List<Categoria> findAllSubCategoriasByCategoriaPadreId(Long idCategoria, Long idSucursal){
        return categoriaRepository.findAllSubCategoriasByCategoriaPadreId(idCategoria,idSucursal);
    }

    @Override
    public Categoria getById(Long id) {
      var categoria = categoriaRepository.getById(id);
        if (categoria == null) {
            throw new RuntimeException("No  existe la categoria con el id: " + id );
        }
       categoria.setArticulos(getArticulosInternosNoEliminados(categoria.getArticulos()));
        categoria.setSubCategorias(getSubcategoriaInternosNoEliminados(categoria.getSubCategorias()));
        return categoria;
    }

    private Set<Articulo> getArticulosInternosNoEliminados(Set<Articulo> articulos) {
        return articulos.stream()
                .filter(art -> !art.isEliminado())
                .collect(Collectors.toSet());
    }

    private Set<Categoria> getSubcategoriaInternosNoEliminados(Set<Categoria> subcategorias) {
        return subcategorias.stream()
                .filter(cat -> !cat.isEliminado())
                .collect(Collectors.toSet());
    }

    @Override
    public  List<Categoria> findAllCategoriasBySucursalId(Long idSucursal){
        return categoriaRepository.findAllCategoriasBySucursalId(idSucursal);
    }

    @Override
    public  List<Categoria> findSubcategoriasBySucursalId( Long idSucursal){
        return categoriaRepository.findSubcategoriasBySucursalId(idSucursal);
    }
}
