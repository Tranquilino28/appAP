# Etapa 1: build con Maven
FROM maven:3.9.6-eclipse-temurin-24 AS builder
WORKDIR /build

# Copiar pom primero para cachear dependencias
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copiar todo
COPY . ./

# Construir el jar
RUN mvn clean package -DskipTests

# Etapa 2: imagen final
FROM eclipse-temurin:24-jdk
WORKDIR /app

COPY --from=builder /build/target/*.jar app.jar

EXPOSE 3066

CMD ["java", "-jar", "app.jar"]
