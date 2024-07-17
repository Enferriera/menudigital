package com.example.menudigital;

import com.example.menudigital.domain.entities.*;
import com.example.menudigital.repositories.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalTime;

@SpringBootApplication
public class MenudigitalApplication {
	private static final Logger logger = LoggerFactory.getLogger(MenudigitalApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MenudigitalApplication.class, args);

		System.out.println(" Funcionando ALberto");
	}

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ArticuloRepository articuloRepository;
	@Autowired
	private AlergenosRepository alergenosRepository;
	@Autowired
	private DomicilioRepository domicilioRepository;
	@Autowired
	private EmpresaRepository empresaRepository;
	@Autowired
	private ImagenArticuloRepository imagenArticuloRepository;
	@Autowired
	private SucursalRepository sucursalRepository;
	@Autowired
	private LocalidadRepository localidadRepository;
	@Autowired
	private ProvinciaRepository provinciaRepository;
	@Autowired
	private PaisRepository paisRepository;


	@Bean
	@Transactional
	CommandLineRunner init(CategoriaRepository categoriaRepository,
						   ArticuloRepository articuloRepository, AlergenosRepository alergenosRepository,
						   DomicilioRepository domicilioRepository, EmpresaRepository empresaRepository,
						   ImagenArticuloRepository imagenArticuloRepository,
						   SucursalRepository sucursalRepository, LocalidadRepository localidadRepository,
						   ProvinciaRepository provinciaRepository, PaisRepository paisRepository) {
		return args -> {
			logger.info("----------------ESTOY----FUNCIONANDO---------------------");
// Etapa del dashboard
			// Crear 1 pais
			// Crear 2 provincias para ese pais
			// crear 2 localidades para cada provincia
			Pais pais1 = Pais.builder().nombre("Argentina").build();
			paisRepository.save(pais1);
			//CREACION DE PROVINCIAS
			Provincia provincia1 = Provincia.builder().nombre("Mendoza").pais(pais1).build();
			Provincia provincia2 = Provincia.builder().nombre("Buenos Aires").pais(pais1).build();
			provinciaRepository.save(provincia1);
			provinciaRepository.save(provincia2);

			//CREACION DE LOCALIDADES
			Localidad localidad1 = Localidad.builder().nombre("Lujan de Cuyo").provincia(provincia1).build();
			Localidad localidad2 = Localidad.builder().nombre("Guaymallen").provincia(provincia1).build();
			Localidad localidad3 = Localidad.builder().nombre("Mar del Plata").provincia(provincia2).build();
			Localidad localidad4 = Localidad.builder().nombre("Mar de las Pampas").provincia(provincia2).build();

			localidadRepository.save(localidad1);
			localidadRepository.save(localidad2);
			localidadRepository.save(localidad3);
			localidadRepository.save(localidad4);

			// CREAR EMPRESAS Y SUCURSALES PARA PRUEBAS

			Empresa mama = Empresa.builder().logo("MAMA.jpeg").nombre("LO DE MAMÁ").cuit(30546790L).razonSocial("Venta de Alimentos").build();
			empresaRepository.save(mama);

			Sucursal mama1 = Sucursal.builder()
					.nombre("Lo de Mamá - Guaymallén")
					.horarioApertura(LocalTime.of(20, 0))
					.horarioCierre(LocalTime.of(0, 0))
					.esCasaMatriz(true)
					.build();

			Domicilio domMama1 = Domicilio.builder().cp(5519).calle("Elpidio González").numero(280).piso(0).nroDpto(0).
					localidad(localidad2).build();

			mama1.setDomicilio(domMama1);
			mama1.setEmpresa(mama);

			sucursalRepository.save(mama1);

			mama.getSucursales().add(mama1);

			empresaRepository.save(mama);

			Alergeno alergMani = Alergeno.builder().denominacion("Mani").build();
			Alergeno alergMarisco = Alergeno.builder().denominacion("Marisco").build();


			alergenosRepository.save(alergMani);
			alergenosRepository.save(alergMarisco);

			Categoria menu= Categoria.builder().denominacion("Menu").build();
			categoriaRepository.save(menu);

			mama1.getCategorias().add(menu);
			sucursalRepository.save(mama1);

			Articulo art1 = Articulo.builder().denominacion("Rizzoto de Mar").precioVenta(10000.00).
					codigo("A0001").build();
			articuloRepository.save(art1);
			art1.getAlergenos().add(alergMarisco);
			art1.setCategoria(menu);

			menu.getArticulos().add(art1);
			articuloRepository.save(art1);

		};
	}

}
