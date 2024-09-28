# Etapa 1: Construcción
FROM alpine:latest as build

# Actualizar el sistema y agregar OpenJDK 17 y bash
RUN apk update
RUN apk add openjdk17 bash

# Copiar el código fuente del proyecto al contenedor
COPY gradlew .
COPY gradle/ gradle/
COPY . .

# Verificar permisos y hacer que gradlew sea ejecutable
RUN chmod +x ./gradlew

# Compilar el proyecto y generar el JAR usando Gradle
RUN ./gradlew bootJar --no-daemon

# Etapa 2: Imagen final de producción
FROM openjdk:17-alpine

# Exponer el puerto que la aplicación utiliza
EXPOSE 8090

# Copiar el archivo .env
COPY .env ./.env

# Copiar el JAR compilado desde la etapa de construcción
COPY --from=build ./build/libs/menudigital-0.0.1-SNAPSHOT.jar ./app.jar

# Comando de inicio para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
