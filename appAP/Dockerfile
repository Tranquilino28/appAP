# Usa una imagen oficial de Java 24
FROM eclipse-temurin:24-jdk

# Crea un directorio dentro del contenedor
WORKDIR /app

# Copia el JAR generado por Maven al contenedor
COPY target/*.jar app.jar

# Expone el puerto 3066 (o el que uses)
EXPOSE 8080

# Comando que ejecuta tu aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]