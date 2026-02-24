# ========= ETAPA 1: BUILD =========
FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /build

# Copiamos todo el proyecto porque es multi-module
COPY . .

# Compilamos solo el módulo gateway
RUN ./mvnw clean package -pl msvc-gateway -am -DskipTests


# ========= ETAPA 2: RUNTIME =========
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiamos el jar generado desde la etapa builder
COPY --from=builder /build/msvc-gateway/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]