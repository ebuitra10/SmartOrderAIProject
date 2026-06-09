# ========= ETAPA 1: BUILD =========
FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /app

COPY . .

RUN ls -la && cat -e mvnw | head -n 5

# Limpieza de seguridad (Esto arregla el "not found" si el archivo tiene formato Windows)
RUN apk add --no-cache sed && \
    sed -i 's/\r$//' mvnw && \
    chmod +x mvnw

# Compilamos solo el módulo gateway
RUN ./mvnw clean package -pl msvc-gateway -am -DskipTests


# ========= ETAPA 2: RUNTIME =========
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiamos el jar generado desde la etapa builder
COPY --from=builder /app/msvc-gateway/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]