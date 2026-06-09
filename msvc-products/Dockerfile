FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /app

COPY . .

RUN ls -la && cat -e mvnw | head -n 5

# Limpieza de seguridad (Esto arregla el "not found" si el archivo tiene formato Windows)
RUN sed -i 's/\r$//' mvnw && \
    chmod +x mvnw

RUN ./mvnw clean package -pl msvc-products -am -DskipTests



FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/msvc-products/target/*.jar app.jar

EXPOSE 8003

ENTRYPOINT ["java","-jar","app.jar"]