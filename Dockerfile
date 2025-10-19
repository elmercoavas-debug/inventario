# Etapa 1: Construcción del proyecto con Maven y Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagen ligera solo con el .jar
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/inventario-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
