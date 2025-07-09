# Etapa 1: construir el .jar
FROM maven:3.9.6-eclipse-temurin-24 as builder
WORKDIR /build

# Copiamos todo el proyecto
COPY . .

# Empaquetamos
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final
FROM eclipse-temurin:24-jdk
WORKDIR /app

# Copiamos el .jar generado en la etapa anterior
COPY --from=builder /build/target/*.jar app.jar

EXPOSE 3066

CMD ["java", "-jar", "app.jar"]