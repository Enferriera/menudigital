# Menu Digital Appi

## Descripción
Este proyecto es una aplicación backend de un gestor de menu para restoran

## Tabla de Contenidos
- [Instalación](#instalación)
- [Uso](#uso)

## Instalación
1. Clona el repositorio:
   ```bash
   git clone https://github.com/Enferriera/menudigital.git
   
## Uso
1. Si estas en windows ejecuta en la raiz del proyecto:
    ```cmd
   docker compose -f docker-compose-dev.yml up --build
   
2. Si estas en linux ejecuta en la raiz del proyecto:
    ```bash
   sudo chmod +x ./gradlew
   sudo docker compose -f docker-compose-dev.yml up --build
   
3. Si deseas llenar la base de datos, antes de ejecutar Docker, elimina las siguientes líneas:

En MenudigitalApplication, elimina las líneas 28 y 730.
En utils->LocalidadesDownloader, elimina las líneas 15 y 74.
Una vez eliminadas estas líneas, ejecuta el paso 2. Al detener el contenedor, debes comentar nuevamente las mismas líneas para volver a ejecutar el contenedor. """
