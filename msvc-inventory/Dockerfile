FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /app

COPY . .

RUN ls -la && cat -e mvnw | head -n 5

# 3. Limpieza de seguridad (Esto arregla el "not found" si el archivo tiene formato Windows)
RUN apk add --no-cache sed && \
    sed -i 's/\r$//' mvnw && \
    chmod +x mvnw

RUN ./mvnw clean package -pl msvc-inventory -am -DskipTests



FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/msvc-inventory/target/*.jar app.jar

EXPOSE 8004

ENTRYPOINT ["java","-jar","app.jar"]

