# Imagen de produccion, en dos etapas: se compila con Maven y solo el jar
# pasa a una imagen con JRE, que pesa bastante menos que la de build.

FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Primero solo el pom: mientras no cambien las dependencias,
# Docker reutiliza esta capa y no vuelve a descargarlas.
COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src
RUN mvn -B clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
