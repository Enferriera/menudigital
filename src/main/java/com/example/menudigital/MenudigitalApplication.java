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

            Empresa benditoRufian = Empresa.builder().logo("https://benditorufian.com/resources/brand.svg").nombre("BENDITO RUFIAN").cuit(30546790L).razonSocial("Venta de Alimentos").build();
            empresaRepository.save(benditoRufian);

            Sucursal rufianBarraca = Sucursal.builder()
                    .nombre("BENDITO RUFIAN - LA BARRACA")
                    .horarioApertura(LocalTime.of(20, 0))
                    .horarioCierre(LocalTime.of(0, 0))
                    .esCasaMatriz(true)
                    .build();

            Domicilio domRufian1 = Domicilio.builder().cp(5519).calle("Las Cañas").numero(1833).piso(0).nroDpto(34).
                    localidad(localidad2).build();

            rufianBarraca.setDomicilio(domRufian1);
            rufianBarraca.setEmpresa(benditoRufian);

            sucursalRepository.save(rufianBarraca);

            benditoRufian.getSucursales().add(rufianBarraca);

            empresaRepository.save(benditoRufian);

            logger.info("----------------Generando Alergenos---------------------");
            Alergeno alergMani = Alergeno.builder().denominacion("Mani").build();
            Alergeno alergMarisco = Alergeno.builder().denominacion("Marisco").build();


            alergenosRepository.save(alergMani);
            alergenosRepository.save(alergMarisco);
            logger.info("----------------Generando Categorias---------------------");
            Categoria menu = Categoria.builder().denominacion("MENU").build();
            categoriaRepository.save(menu);
            Categoria cafeteria = Categoria.builder().denominacion("CAFETERIA").build();
            categoriaRepository.save(cafeteria);
            Categoria cocktails = Categoria.builder().denominacion("COCKTAILS").build();
            categoriaRepository.save(cocktails);
            Categoria vinoPorCopa = Categoria.builder().denominacion("VINO POR COPA").build();
            categoriaRepository.save(vinoPorCopa);
            logger.info("----------------Generando Subcategorias---------------------");
            Categoria promociones = Categoria.builder().denominacion("PROMOCIONES").categoriaPadre(menu).build();
            categoriaRepository.save(promociones);

            Categoria entradas = Categoria.builder().denominacion("ENTRADAS").categoriaPadre(menu).build();
            categoriaRepository.save(entradas);
            Categoria tapas = Categoria.builder().denominacion("TAPAS").categoriaPadre(menu).build();
            categoriaRepository.save(tapas);
            Categoria torresPicadas = Categoria.builder().denominacion("TORRES Y PICADAS").categoriaPadre(menu).build();

            categoriaRepository.save(torresPicadas);
            Categoria principales = Categoria.builder().denominacion("PRINCIPALES").categoriaPadre(menu).build();
            categoriaRepository.save(principales);
            Categoria entrePanes = Categoria.builder().denominacion("ENTRE PANES").categoriaPadre(menu).build();
            categoriaRepository.save(entrePanes);
            Categoria postres = Categoria.builder().denominacion("POSTRES").categoriaPadre(menu).build();
            categoriaRepository.save(postres);
            Categoria bebidas = Categoria.builder().denominacion("BEBIDAS").categoriaPadre(menu).build();
            categoriaRepository.save(bebidas);

            menu.getSubCategorias().add(entradas);
            menu.getSubCategorias().add(tapas);
            menu.getSubCategorias().add(torresPicadas);
			menu.getSubCategorias().add(principales);
            menu.getSubCategorias().add(entrePanes);
            menu.getSubCategorias().add(postres);
            menu.getSubCategorias().add(bebidas);
            categoriaRepository.save(menu);

            Categoria promocionesBienRufianas= Categoria.builder().denominacion("PROMOCIONES BIEN RUFINAS").categoriaPadre(cafeteria).build();
            categoriaRepository.save(promocionesBienRufianas);
            Categoria infusionesBebidas = Categoria.builder().denominacion("INFUSIONES Y BEBIDAS").categoriaPadre(cafeteria).build();
            categoriaRepository.save(infusionesBebidas);
            Categoria bakeryBrunch= Categoria.builder().denominacion("BAKERY / BRUNCH").categoriaPadre(cafeteria).build();
            categoriaRepository.save(bakeryBrunch);

            cafeteria.getSubCategorias().add(promocionesBienRufianas);
            cafeteria.getSubCategorias().add(infusionesBebidas);
            cafeteria.getSubCategorias().add(bakeryBrunch);
            categoriaRepository.save(cafeteria);

            Categoria happyHour = Categoria.builder().denominacion("HAPPY HOUR").categoriaPadre(cocktails).build();
            categoriaRepository.save(happyHour);
            Categoria benditosCoctelesDeAutor = Categoria.builder().denominacion("BENDITOS COCTELES DE AUTOR").categoriaPadre(cocktails).build();
            categoriaRepository.save(benditosCoctelesDeAutor);
            Categoria vermu= Categoria.builder().denominacion("VERMU").categoriaPadre(cocktails).build();
            categoriaRepository.save(vermu);
            Categoria cocteleriaClasica = Categoria.builder().denominacion("COCTELERIA CLASICA").categoriaPadre(cocktails).build();
            categoriaRepository.save(cocteleriaClasica);
            Categoria medidas= Categoria.builder().denominacion("MEDIDAS").categoriaPadre(cocktails).build();
            categoriaRepository.save(medidas);
            Categoria ginsTonicos = Categoria.builder().denominacion("GINS Y TONICOS").categoriaPadre(cocktails).build();
            categoriaRepository.save(ginsTonicos);

            cocktails.getSubCategorias().add(happyHour);
            cocktails.getSubCategorias().add(benditosCoctelesDeAutor);
            cocktails.getSubCategorias().add(vermu);
            cocktails.getSubCategorias().add(cocteleriaClasica);
            cocktails.getSubCategorias().add(medidas);
            cocktails.getSubCategorias().add(ginsTonicos);
            categoriaRepository.save(cocktails);

            Categoria vinoTirado= Categoria.builder().denominacion("VINO TIRADO").categoriaPadre(vinoPorCopa).build();
            categoriaRepository.save(vinoTirado);

            categoriaRepository.save(vinoPorCopa);

            rufianBarraca.getCategorias().add(menu);
            rufianBarraca.getCategorias().add(cafeteria);
            rufianBarraca.getCategorias().add(cocktails);
            rufianBarraca.getCategorias().add(vinoPorCopa);
            sucursalRepository.save(rufianBarraca);

            logger.info("----------------Generando Articulos---------------------");


            ImagenArticulo imagenPromo1= ImagenArticulo.builder().name("promo1").url("https://benditorufian.com/resources/items/907.jpg").build();
            imagenArticuloRepository.save(imagenPromo1);
            Articulo papasCerveza = Articulo.builder().denominacion("PAPAS + CERVEZA").precioVenta(6300.00).
                    codigo("P0001").descripcion("PROMO VALIDA DE 16 HS. A 21 HS. PAPAS RUSTICAS + 2 PINTAS").build();
            articuloRepository.save(papasCerveza);

            papasCerveza.getImagenes().add(imagenPromo1);
            papasCerveza.setCategoria(promociones);

            promociones.getArticulos().add(papasCerveza);
            categoriaRepository.save(promociones);
            articuloRepository.save(papasCerveza);

            ImagenArticulo imagenPromo2= ImagenArticulo.builder().name("promo2").url("https://benditorufian.com/resources/items/1110.jpg").build();
            imagenArticuloRepository.save(imagenPromo2);
            Articulo vendimiaParaDos = Articulo.builder().denominacion("PROMO VENDIMIA PARA DOS OPCION 2").precioVenta(30000.00).
                    codigo("P0002").descripcion("Opcion 2: Torre con ternera, chorizo, bondiola  y pollo, todo ahumado en nuestro ahumado rufián. Acompañado de verduras a la chapa, ensalada mixta y coleslaw  +  botella de vino santa julia").build();
            articuloRepository.save(vendimiaParaDos);

            vendimiaParaDos.getImagenes().add(imagenPromo2);
            vendimiaParaDos.setCategoria(promociones);

            promociones.getArticulos().add(vendimiaParaDos);
            categoriaRepository.save(promociones);
            articuloRepository.save(vendimiaParaDos);

            ImagenArticulo imagenPromo3= ImagenArticulo.builder().name("promo3").url("https://benditorufian.com/resources/items/1101.jpg").build();
            imagenArticuloRepository.save(imagenPromo3);
            Articulo menuEjecutivo1 = Articulo.builder().denominacion("MENU EJECUTIVO 1 MEDIO DIA").precioVenta(8200.00).
                    codigo("P0003").descripcion("Wraps de suprema de pollo y palta.\n" +
                            "Incluye Bebida + Postre o Café. Opción válida de lunes a viernes sin contar los feriados ").build();
            articuloRepository.save(menuEjecutivo1);

            menuEjecutivo1.getImagenes().add(imagenPromo3);
            menuEjecutivo1.setCategoria(promociones);

            promociones.getArticulos().add(menuEjecutivo1);
            categoriaRepository.save(promociones);
            articuloRepository.save(menuEjecutivo1);

            ImagenArticulo imagenEntrada1= ImagenArticulo.builder().name("entrada1").url("https://benditorufian.com/resources/items/139.jpg").build();
            imagenArticuloRepository.save(imagenEntrada1);
            Articulo papasRusticas = Articulo.builder().denominacion("PAPAS RUSTICAS").precioVenta(5150.00).
                    codigo("E0001").build();
            articuloRepository.save(papasRusticas);

            papasRusticas.getImagenes().add(imagenEntrada1);
            papasRusticas.setCategoria(entradas);

            entradas.getArticulos().add(papasRusticas);
            categoriaRepository.save(entradas);
            articuloRepository.save(papasRusticas);


            ImagenArticulo imagenEntrada2= ImagenArticulo.builder().name("entrada2").url("https://benditorufian.com/resources/items/63.jpg").build();
            imagenArticuloRepository.save(imagenEntrada2);
            Articulo benditasRabas = Articulo.builder().denominacion("BENDITAS SEAN LAS RABAS").precioVenta(14700.00).
                    codigo("E0002").descripcion("Aros de calamar apanados con salsa de barbacoa y suave salsa de alioli y limón").build();
            articuloRepository.save(benditasRabas);

            benditasRabas.getImagenes().add(imagenEntrada2);
            benditasRabas.setCategoria(entradas);

            entradas.getArticulos().add(benditasRabas);
            categoriaRepository.save(entradas);
            articuloRepository.save(benditasRabas);


            ImagenArticulo imagenTapa1= ImagenArticulo.builder().name("tapa1").url("https://benditorufian.com/resources/items/954.jpg").build();
            imagenArticuloRepository.save(imagenTapa1);
            Articulo kachapurisJamon = Articulo.builder().denominacion("KACHAPURIS DE JAMON CRUDO").precioVenta(5650.00).
                    codigo("T0001").descripcion("Tortilla de yogurt, queso parmesano y pategras. Cubierta de ricota casera, rúcula, jamón crudo y tomates confitados.").build();
            articuloRepository.save(kachapurisJamon);

            kachapurisJamon.getImagenes().add(imagenTapa1);
            kachapurisJamon.setCategoria(tapas);

            tapas.getArticulos().add(kachapurisJamon);
            categoriaRepository.save(tapas);
            articuloRepository.save(kachapurisJamon);

            ImagenArticulo imagenTapa2= ImagenArticulo.builder().name("tapa2").url("https://benditorufian.com/resources/items/963.jpg").build();
            imagenArticuloRepository.save(imagenTapa2);
            Articulo perasRellenas = Articulo.builder().denominacion("PERAS EN CURCUMA RELLENAS").precioVenta(5000.00).
                    codigo("T0002").descripcion("Compota de pera en cúrcuma y espumante, gratinada con queso azul con flor de panceta crocante.").build();
            articuloRepository.save(perasRellenas);

            perasRellenas.getImagenes().add(imagenTapa2);
            perasRellenas.setCategoria(tapas);

            tapas.getArticulos().add(perasRellenas);
            categoriaRepository.save(tapas);
            articuloRepository.save(perasRellenas);


            ImagenArticulo imagenPicada1= ImagenArticulo.builder().name("picada1").url("https://benditorufian.com/resources/items/32.jpg").build();
            imagenArticuloRepository.save(imagenPicada1);
            Articulo benditaTorre = Articulo.builder().denominacion("LA BENDITA TORRE").precioVenta(29000.00).
                    codigo("TP0001").descripcion("Empanadas de carne, empanadas de espinaca y queso azul, tapas ahumadas, mini degustacion de quesos, buñuelos de espinaca y cabutia, kachapuris de jamon crudo").build();
            articuloRepository.save(benditaTorre);

            benditaTorre.getImagenes().add(imagenPicada1);
            benditaTorre.setCategoria(torresPicadas);

            torresPicadas.getArticulos().add(benditaTorre);
            categoriaRepository.save(torresPicadas);
            articuloRepository.save(benditaTorre);


            ImagenArticulo imagenPicada2= ImagenArticulo.builder().name("picada2").url("https://benditorufian.com/resources/items/33.jpg").build();
            imagenArticuloRepository.save(imagenPicada2);
            Articulo perseguidaPorLosVinos = Articulo.builder().denominacion("PERSEGUIDA POR LOS VINOS PARA 2/3 PERSONAS").precioVenta(27900.00).
                    codigo("TP0002").descripcion("Tabla de quesos, fiambres y delicatesen ideales para maridar.").build();
            articuloRepository.save(perseguidaPorLosVinos);

            perseguidaPorLosVinos.getImagenes().add(imagenPicada2);
            perseguidaPorLosVinos.setCategoria(torresPicadas);

            torresPicadas.getArticulos().add(perseguidaPorLosVinos);
            categoriaRepository.save(torresPicadas);
            articuloRepository.save(perseguidaPorLosVinos);



            ImagenArticulo imagenPrincipal1= ImagenArticulo.builder().name("principal1").url("https://benditorufian.com/resources/items/19.jpg").build();
            imagenArticuloRepository.save(imagenPrincipal1);
            Articulo sorrentinosSalmon = Articulo.builder().denominacion("SORRENTINOS MALECHORES DE SALMON").precioVenta(17500.00).
                    codigo("PR0001").descripcion("Pasta casera rellena de salmón ahumado, en nuestro rufián ahumador con suave crema de ciboulette y verdeo acompañado de queso parmessano").build();
            articuloRepository.save(sorrentinosSalmon);

            sorrentinosSalmon.getImagenes().add(imagenPrincipal1);
            sorrentinosSalmon.setCategoria(principales);

            principales.getArticulos().add(sorrentinosSalmon);
            categoriaRepository.save(principales);
            articuloRepository.save(sorrentinosSalmon);


            ImagenArticulo imagenPrincipal2= ImagenArticulo.builder().name("principal2").url("https://benditorufian.com/resources/items/20.jpg").build();
            imagenArticuloRepository.save(imagenPrincipal2);
            Articulo ensaladaSincera = Articulo.builder().denominacion("ENSALADA BIEN SINCERA").precioVenta(8950.00).
                    codigo("PR0002").descripcion("(Rúcula, mix de verdes, repollo blanco, mantecosa, palta, cherrys,\n" +
                            "semillas tostadas, huevo, cebolla caramelizadas, quesos)").build();
            articuloRepository.save(ensaladaSincera);

            ensaladaSincera.getImagenes().add(imagenPrincipal2);
            ensaladaSincera.setCategoria(principales);

            principales.getArticulos().add(ensaladaSincera);
            categoriaRepository.save(principales);
            articuloRepository.save(ensaladaSincera);


            ImagenArticulo imagenEntrePanes1= ImagenArticulo.builder().name("entrePanes1").url("https://benditorufian.com/resources/items/24.jpg").build();
            imagenArticuloRepository.save(imagenEntrePanes1);
            Articulo bondiolita = Articulo.builder().denominacion("BONDIOLITA BIEN BANDIDA").precioVenta(14350.00).
                    codigo("EP0001").descripcion("Pan tierno del rufián, Cerdito asado, panceta, cebollas y pimientos caramelizados, queso derretido, rúcula, y salsa de barbacoa.\n" +
                            "Con opción sin TACC").build();
            articuloRepository.save(bondiolita);

            bondiolita.getImagenes().add(imagenEntrePanes1);
            bondiolita.setCategoria(entrePanes);

            entrePanes.getArticulos().add(bondiolita);
            categoriaRepository.save(entrePanes);
            articuloRepository.save(bondiolita);


            ImagenArticulo imagenEntrePanes2= ImagenArticulo.builder().name("entrePanes2").url("https://benditorufian.com/resources/items/27.jpg").build();
            imagenArticuloRepository.save(imagenEntrePanes2);
            Articulo hamburguesaClasica = Articulo.builder().denominacion("HAMBURGUESA CLASICA AHUMADA").precioVenta(9600.00).
                    codigo("EP0002").descripcion("Pan casero con medallón de carne ahumada , mozzarella, lechuga, tomate, huevo frito, barbacoa y mayonesa casera + papas rústicas fritas.\n" +
                            "Con opción sin TACC\n" +
                            "Extra medallon de carne $2000").build();
            articuloRepository.save(hamburguesaClasica);

            hamburguesaClasica.getImagenes().add(imagenEntrePanes2);
            hamburguesaClasica.setCategoria(entrePanes);

            entrePanes.getArticulos().add(hamburguesaClasica);
            categoriaRepository.save(entrePanes);
            articuloRepository.save(hamburguesaClasica);


            Articulo jarritoBendito = Articulo.builder().denominacion("JARRITO BENDITO").precioVenta(4050.00).
                    codigo("PO0001").descripcion("DULCE DE LECHE, CHOCOLATE, AMERICANA").build();
            articuloRepository.save(jarritoBendito);


            jarritoBendito.setCategoria(postres);

            postres.getArticulos().add(jarritoBendito);
            categoriaRepository.save(postres);
            articuloRepository.save(jarritoBendito);


            Articulo brownieHelado = Articulo.builder().denominacion("BROWNIE CON HELADO").precioVenta(5200.00).
                    codigo("PO0002").build();
            articuloRepository.save(brownieHelado);


            brownieHelado.setCategoria(postres);

            postres.getArticulos().add(brownieHelado);
            categoriaRepository.save(postres);
            articuloRepository.save(brownieHelado);

            Articulo aguaSaborizada = Articulo.builder().denominacion("AGUAS SABORIZADAS 500 CC").precioVenta(2250.00).
                    codigo("B0001").build();
            articuloRepository.save(aguaSaborizada);


            aguaSaborizada.setCategoria(bebidas);
            bebidas.getArticulos().add(aguaSaborizada);
            categoriaRepository.save(bebidas);
            articuloRepository.save(aguaSaborizada);


            Articulo cervezaTirada = Articulo.builder().denominacion("CERVEZA TIRADA").precioVenta(3250.00).
                    codigo("B0001").descripcion("CERVEZA ARTESANAL PINTA 550 ML").build();
            articuloRepository.save(cervezaTirada);


            cervezaTirada.setCategoria(bebidas);
            bebidas.getArticulos().add(cervezaTirada);
            categoriaRepository.save(bebidas);
            articuloRepository.save(cervezaTirada);


            ImagenArticulo imagenCafePromo1= ImagenArticulo.builder().name("cafePromo1").url("https://benditorufian.com/resources/items/88.jpg").build();
            imagenArticuloRepository.save(imagenCafePromo1);
            Articulo cafeComboBandido = Articulo.builder().denominacion("COMBO BANDIDOS DE SIEMPRE").precioVenta(2900.00).
                    codigo("CP0001").descripcion("Infusión, 1 medialuna de la ostia, jugo de naranja chico").build();
            articuloRepository.save(cafeComboBandido);

            cafeComboBandido.getImagenes().add(imagenCafePromo1);
            cafeComboBandido.setCategoria(promocionesBienRufianas);

            promocionesBienRufianas.getArticulos().add(cafeComboBandido);
            categoriaRepository.save(promocionesBienRufianas);
            articuloRepository.save(cafeComboBandido);


            ImagenArticulo imagenCafePromo2= ImagenArticulo.builder().name("cafePromo2").url("https://benditorufian.com/resources/items/91.jpg").build();
            imagenArticuloRepository.save(imagenCafePromo2);
            Articulo cafeComboMinitorta = Articulo.builder().denominacion("COMBO MINITORTA ENCARCELADA").precioVenta(6900.00).
                    codigo("CP0002").descripcion("Infusión, mini torta encarcelada de la semana, jugo chico").build();
            articuloRepository.save(cafeComboMinitorta);

            cafeComboMinitorta.getImagenes().add(imagenCafePromo2);
            cafeComboMinitorta.setCategoria(promocionesBienRufianas);

            promocionesBienRufianas.getArticulos().add(cafeComboMinitorta);
            categoriaRepository.save(promocionesBienRufianas);
            articuloRepository.save(cafeComboMinitorta);


            ImagenArticulo imagenCafeInfusion1= ImagenArticulo.builder().name("cafeInfusion1").url("https://benditorufian.com/resources/items/65.jpg").build();
            imagenArticuloRepository.save(imagenCafeInfusion1);
            Articulo cafeExpresso = Articulo.builder().denominacion("EXPRESSO").precioVenta(2000.00).
                    codigo("CI0001").descripcion("CAFE, CORTADO, LAGRIMA, RISTRETTO").build();
            articuloRepository.save(cafeExpresso);

            cafeExpresso.getImagenes().add(imagenCafeInfusion1);
            cafeExpresso.setCategoria(infusionesBebidas);

            infusionesBebidas.getArticulos().add(cafeExpresso);
            categoriaRepository.save(infusionesBebidas);
            articuloRepository.save(cafeExpresso);


            ImagenArticulo imagenCafeInfusion2= ImagenArticulo.builder().name("cafeInfusion2").url("https://benditorufian.com/resources/items/69.jpg").build();
            imagenArticuloRepository.save(imagenCafeInfusion2);
            Articulo cafeCapuccino = Articulo.builder().denominacion("CAPUCCINO").precioVenta(3200.00).
                    codigo("CI0002").build();
            articuloRepository.save(cafeCapuccino);

            cafeCapuccino.getImagenes().add(imagenCafeInfusion2);
            cafeCapuccino.setCategoria(infusionesBebidas);

            infusionesBebidas.getArticulos().add(cafeCapuccino);
            categoriaRepository.save(infusionesBebidas);
            articuloRepository.save(cafeCapuccino);


            ImagenArticulo imagenCafeBakery1= ImagenArticulo.builder().name("cafeBakery1").url("https://benditorufian.com/resources/items/945.jpg").build();
            imagenArticuloRepository.save(imagenCafeBakery1);
            Articulo tostadaPerpetuas = Articulo.builder().denominacion("TOSTADAS PERPETUAS").precioVenta(4550.00).
                    codigo("CB0001").descripcion("2 unid, queso crema, huevo y bacon\n" +
                            "Con opción Sin TACC").build();
            articuloRepository.save(tostadaPerpetuas);

            tostadaPerpetuas.getImagenes().add(imagenCafeBakery1);
            tostadaPerpetuas.setCategoria(bakeryBrunch);

            bakeryBrunch.getArticulos().add(tostadaPerpetuas);
            categoriaRepository.save(bakeryBrunch);
            articuloRepository.save(tostadaPerpetuas);

            ImagenArticulo imagenCafeBakery2= ImagenArticulo.builder().name("cafeBakery2").url("https://benditorufian.com/resources/items/953.jpg").build();
            imagenArticuloRepository.save(imagenCafeBakery2);
            Articulo perfectoAtrevido = Articulo.builder().denominacion("PERFECTO ATREVIDO").precioVenta(5550.00).
                    codigo("CB0002").descripcion("Tostón de campo con queso feta, palta, huevo estrellado y verdes").build();
            articuloRepository.save(perfectoAtrevido);

            perfectoAtrevido.getImagenes().add(imagenCafeBakery2);
            perfectoAtrevido.setCategoria(bakeryBrunch);

            bakeryBrunch.getArticulos().add(perfectoAtrevido);
            categoriaRepository.save(bakeryBrunch);
            articuloRepository.save(perfectoAtrevido);


            ImagenArticulo imagenHappyHour1= ImagenArticulo.builder().name("hapyHour1").url("https://benditorufian.com/resources/items/195.jpg").build();
            imagenArticuloRepository.save(imagenHappyHour1);
            Articulo happyHourCocktail = Articulo.builder().denominacion("HAPPY HOUR COCKTAILS (CLASICOS)").precioVenta(6100.00).
                    codigo("HH0001").descripcion("(De 16:00 a 21:00 hs.)\n" +
                            "Branca & Cola,\n" +
                            "Gin tonic nacional,\n" +
                            "Aperol spritz,\n" +
                            "Cuba libre,\n" +
                            "Vermu la fuerza,\n" +
                            "mojito,\n" +
                            "caipiroska, gancia y campari").build();
            articuloRepository.save(happyHourCocktail);

            happyHourCocktail.getImagenes().add(imagenHappyHour1);
            happyHourCocktail.setCategoria(happyHour);

            happyHour.getArticulos().add(happyHourCocktail);
            categoriaRepository.save(happyHour);
            articuloRepository.save(happyHourCocktail);


            Articulo happyHourCarpanoBianco = Articulo.builder().denominacion("CARPANO BIANCO 2X1").precioVenta(4300.00).
                    codigo("HH0002").descripcion("HASTA LAS 00").build();
            articuloRepository.save(happyHourCarpanoBianco);


            happyHourCarpanoBianco.setCategoria(happyHour);

            happyHour.getArticulos().add(happyHourCarpanoBianco);
            categoriaRepository.save(happyHour);
            articuloRepository.save(happyHourCarpanoBianco);


            ImagenArticulo imagenBenditoCocktail1= ImagenArticulo.builder().name("benditoCocktail1").url("https://benditorufian.com/resources/items/178.jpg").build();
            imagenArticuloRepository.save(imagenBenditoCocktail1);
            Articulo sweetCream = Articulo.builder().denominacion("SWEET CREAM").precioVenta(10800.00).
                    codigo("CKB0001").descripcion("Frutos rojos, licor Baileys, ron Havana, ron Malibú, crema.\n" +
                            "\n" +
                            "Coctel cremoso, pero en conjunto a los frutos rojos y el coco le dan unas notas tropicales, para poder disfrutar de un coctel cremoso pero fresco. ").build();
            articuloRepository.save(sweetCream);

            sweetCream.getImagenes().add(imagenBenditoCocktail1);
            sweetCream.setCategoria(benditosCoctelesDeAutor);

            benditosCoctelesDeAutor.getArticulos().add(sweetCream);
            categoriaRepository.save(benditosCoctelesDeAutor);
            articuloRepository.save(sweetCream);


            ImagenArticulo imagenBenditoCocktail2= ImagenArticulo.builder().name("benditoCocktail2").url("https://benditorufian.com/resources/items/176.jpg").build();
            imagenArticuloRepository.save(imagenBenditoCocktail2);
            Articulo elCazador = Articulo.builder().denominacion("EL CAZADOR").precioVenta(7350.00).
                    codigo("CKB0002").descripcion("Licor Jagermeister, ron Bacardi blanco, jugo cítrico, almíbar de romero y pomelo.\n" +
                            "\n" +
                            "Cóctel fresco, donde predominan el sabor a las hierbas y el dulzor mentolado propio del Jagger, con leve dejo amargo en combinación con el pomelo.").build();
            articuloRepository.save(elCazador);

            elCazador.getImagenes().add(imagenBenditoCocktail2);
            elCazador.setCategoria(benditosCoctelesDeAutor);

            benditosCoctelesDeAutor.getArticulos().add(elCazador);
            categoriaRepository.save(benditosCoctelesDeAutor);
            articuloRepository.save(elCazador);



            Articulo carpanoRosso = Articulo.builder().denominacion("CARPANO ROSSO").precioVenta(4300.00).
                    codigo("V0001").descripcion("Vermu soda,\n" +
                            "Vermu Tonic,\n" +
                            "Vermu Pomelo").build();
            articuloRepository.save(carpanoRosso);


            carpanoRosso.setCategoria(vermu);

            vermu.getArticulos().add(carpanoRosso);
            categoriaRepository.save(vermu);
            articuloRepository.save(carpanoRosso);


            Articulo lunfaRosado = Articulo.builder().denominacion("LUNFA ROSADO").precioVenta(5000.00).
                    codigo("V0002").descripcion("Vermu soda,\n" +
                            "Vermu Tonic,\n" +
                            "Vermu Pomelo").build();
            articuloRepository.save(lunfaRosado);


            lunfaRosado.setCategoria(vermu);

            vermu.getArticulos().add(lunfaRosado);
            categoriaRepository.save(vermu);
            articuloRepository.save(lunfaRosado);

            Articulo brancaCola = Articulo.builder().denominacion("BRANCA & COLA").precioVenta(6100.00).
                    codigo("CC0001").descripcion("FERNTET CON COCA COLA").build();
            articuloRepository.save(brancaCola);


            brancaCola.setCategoria(cocteleriaClasica);

            cocteleriaClasica.getArticulos().add(brancaCola);
            categoriaRepository.save(cocteleriaClasica);
            articuloRepository.save(brancaCola);


            Articulo aperolSpritz = Articulo.builder().denominacion("APEROL SPRITZ").precioVenta(4650.00).
                    codigo("CC0002").descripcion("ESPUMANTE, APEROL, AGUA CON GAS, RODAJA Y PIEL DE NARANJA").build();
            articuloRepository.save(aperolSpritz);


            aperolSpritz.setCategoria(cocteleriaClasica);

            cocteleriaClasica.getArticulos().add(aperolSpritz);
            categoriaRepository.save(cocteleriaClasica);
            articuloRepository.save(aperolSpritz);


            Articulo medidaJackDaniels = Articulo.builder().denominacion("MEDIDA JACK DANIEL ´S").precioVenta(10300.00).
                    codigo("CM0001").descripcion("AMERICAN WHISKEY").build();
            articuloRepository.save(medidaJackDaniels);


            medidaJackDaniels.setCategoria(medidas);

            medidas.getArticulos().add(medidaJackDaniels);
            categoriaRepository.save(medidas);
            articuloRepository.save(medidaJackDaniels);

            Articulo medidaBaileys = Articulo.builder().denominacion("MEDIDA BAILEYS").precioVenta(7500.00).
                    codigo("CM0002").descripcion("LICOR DE CREMA").build();
            articuloRepository.save(medidaBaileys);


            medidaBaileys.setCategoria(medidas);

            medidas.getArticulos().add(medidaBaileys);
            categoriaRepository.save(medidas);
            articuloRepository.save(medidaBaileys);


            Articulo principeApostolesTonic = Articulo.builder().denominacion("PRINCIPE DE LOS APOSTOLES TONIC").precioVenta(4900.00).
                    codigo("GT0001").descripcion("-ROSA MOSQUETA -MATE -FUERZA GAUCHA").build();
            articuloRepository.save(principeApostolesTonic);


            principeApostolesTonic.setCategoria(ginsTonicos);

            ginsTonicos.getArticulos().add(principeApostolesTonic);
            categoriaRepository.save(ginsTonicos);
            articuloRepository.save(principeApostolesTonic);

            Articulo tanquerayTonic = Articulo.builder().denominacion("TANQUERAY TONIC").precioVenta(8550.00).
                    codigo("GT0002").descripcion("TANQUERAY SEVILLA. TANQUERAY LONDON DRY").build();
            articuloRepository.save(tanquerayTonic);


            tanquerayTonic.setCategoria(ginsTonicos);

            ginsTonicos.getArticulos().add(tanquerayTonic);
            categoriaRepository.save(ginsTonicos);
            articuloRepository.save(tanquerayTonic);


            Articulo vinosTirados = Articulo.builder().denominacion("NUESTROS VINOS TIRADOS- WINE ON TAP").precioVenta(8550.00).
                    codigo("VT0001").descripcion("Disfruta de nuestros vinos tirados, expendidos directamente de nuestros tanques de acero inoxidable. Contenidos en su temperatura ideal y sin presencia de oxígeno para mantener en forma óptima su estructura, aromas y sabor.").build();
            articuloRepository.save(vinosTirados);


            vinosTirados.setCategoria(vinoTirado);

            vinoTirado.getArticulos().add(vinosTirados);
            categoriaRepository.save(vinoTirado);
            articuloRepository.save(vinosTirados);


            Articulo copaVino = Articulo.builder().denominacion("COPA DE VINO").precioVenta(3000.00).
                    codigo("VT0002").descripcion("Bodega RUTAS SANMARTINIANAS -\n" +
                            "Región del viñedo: Pedriel, Luján de Cuyo. -\n" +
                            "Tiempo en barrica: 0 meses -\n" +
                            "Enólogo: Jorge Benites -").build();
            articuloRepository.save(copaVino);


            copaVino.setCategoria(vinoTirado);

            vinoTirado.getArticulos().add(copaVino);
            categoriaRepository.save(vinoTirado);
            articuloRepository.save(copaVino);



        };
    }

}
