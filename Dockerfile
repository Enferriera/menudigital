# Etapa 1: Construcción
FROM alpine:latest as build

# Actualizar el sistema y agregar OpenJDK 17 y bash
RUN apk update && apk add openjdk17 bash

# Crear el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el código fuente y el archivo de Gradle Wrapper al contenedor
COPY . /app

# Verificar permisos y hacer que gradlew sea ejecutable
RUN chmod +x ./gradlew

# Compilar el proyecto y generar el JAR usando Gradle
RUN ./gradlew bootJar --no-daemon

# Etapa 2: Imagen final de producción
FROM openjdk:17-alpine

# Exponer el puerto que la aplicación utiliza
EXPOSE 8090

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el JAR compilado desde la etapa de construcción
COPY --from=build /app/build/libs/menudigital-0.0.1-SNAPSHOT.jar ./app.jar

# Comando de inicio para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "./app.jar"]
