# Etapa 1: construir el .jar

FROM eclipse-temurin:24-jdk AS builder
WORKDIR /build

# Instalar Maven manualmente
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:24-jdk
WORKDIR /app

COPY --from=builder /build/target/*.jar app.jar

EXPOSE 3066

CMD ["java", "-jar", "app.jar"]
