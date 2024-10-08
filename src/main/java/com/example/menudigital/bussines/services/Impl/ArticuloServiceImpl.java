package com.example.menudigital.bussines.services.Impl;

import com.example.menudigital.bussines.services.ArticuloService;
import com.example.menudigital.bussines.services.CloudinaryService;
import com.example.menudigital.bussines.services.base.BaseServiceImp;
import com.example.menudigital.domain.entities.Articulo;
import com.example.menudigital.domain.entities.ImagenArticulo;
import com.example.menudigital.repositories.ArticuloRepository;
import com.example.menudigital.repositories.ImagenArticuloRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticuloServiceImpl extends BaseServiceImp<Articulo,Long> implements ArticuloService {

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ImagenArticuloRepository imagenArticuloRepository;
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

    // Método para obtener todas las imágenes almacenadas
    @Override
    //pedimos el id para retornar solo las imagenes de un articulo
    public ResponseEntity<List<Map<String, Object>>> getAllImagesByArticuloId(Long id) {
        try {
            // Consultar todas las imágenes desde la base de datos
            List<ImagenArticulo> images = baseRepository.getById(id).getImagenes().stream().toList();
            List<Map<String, Object>> imageList = new ArrayList<>();

            // Convertir cada imagen en un mapa de atributos para devolver como JSON
            for (ImagenArticulo image : images) {
                Map<String, Object> imageMap = new HashMap<>();
                imageMap.put("id", image.getId());
                imageMap.put("name", image.getName());
                imageMap.put("url", image.getUrl());
                imageList.add(imageMap);
            }

            // Devolver la lista de imágenes como ResponseEntity con estado OK (200)
            return ResponseEntity.ok(imageList);
        } catch (Exception e) {
            e.printStackTrace();
            // Devolver un error interno del servidor (500) si ocurre alguna excepción
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Método para subir imágenes a Cloudinary y guardar los detalles en la base de datos
    @Override
    //Pedimos el id de articulo para saber a que articulo asignar las imagenes
    public ResponseEntity<String> uploadImages(MultipartFile[] files, Long idArticulo) {
        List<String> urls = new ArrayList<>();
        var articuloManufacturado = baseRepository.getById(idArticulo);
        //por medio de un condicional limitamos la carga de imagenes a un maximo de 3 por aticulo
        //en caso de tratar de excer ese limite arroja un codigo 413 con el mensaje La cantidad maxima de imagenes es 3
        if(articuloManufacturado.getImagenes().size() >= 3)
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("La cantidad maxima de imagenes es 3");
        try {
            // Iterar sobre cada archivo recibido
            for (MultipartFile file : files) {
                // Verificar si el archivo está vacío
                if (file.isEmpty()) {
                    return ResponseEntity.badRequest().build();
                }

                // Crear una entidad Image y establecer su nombre y URL (subida a Cloudinary)
                ImagenArticulo image = new ImagenArticulo();
                image.setName(file.getOriginalFilename()); // Establecer el nombre del archivo original
                image.setUrl(cloudinaryService.uploadFile(file)); // Subir el archivo a Cloudinary y obtener la URL

                // Verificar si la URL de la imagen es nula (indicativo de fallo en la subida)
                if (image.getUrl() == null) {
                    return ResponseEntity.badRequest().build();
                }

                //Se asignan las imagenes al articuloManufacturado
                articuloManufacturado.getImagenes().add(image);
                //Se guarda la imagen en la base de datos
                //  imagenArticuloRepository.save(image);
                // Agregar la URL de la imagen a la lista de URLs subidas
                urls.add(image.getUrl());
            }

            //se actualiza el insumo en la base de datos con las imagenes
            baseRepository.save(articuloManufacturado);

            // Convertir la lista de URLs a un objeto JSON y devolver como ResponseEntity con estado OK (200)
            return new ResponseEntity<>("{\"status\":\"OK\", \"urls\":" + urls + "}", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            // Devolver un error (400) si ocurre alguna excepción durante el proceso de subida
            return new ResponseEntity<>("{\"status\":\"ERROR\", \"message\":\"" + e.getMessage() + "\"}", HttpStatus.BAD_REQUEST);
        }
    }

    // Método para eliminar una imagen por su identificador en la base de datos y en Cloudinary
    @Override
    public ResponseEntity<String> deleteImage(String publicId, Long id) {
        try {
            // Eliminar la imagen de la base de datos usando su identificador
            imagenArticuloRepository.deleteById(id);

            // Llamar al servicio de Cloudinary para eliminar la imagen por su publicId
            return cloudinaryService.deleteImage(publicId);

        } catch (Exception e) {
            e.printStackTrace();
            // Devolver un error (400) si ocurre alguna excepción durante la eliminación
            return new ResponseEntity<>("{\"status\":\"ERROR\", \"message\":\"" + e.getMessage() + "\"}", HttpStatus.BAD_REQUEST);
        }
    }




}
