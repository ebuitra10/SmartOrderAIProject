FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /app

COPY . .

RUN ./mvnw clean package -pl msvc-products-orders -am -DskipTests



FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/msvc-products-orders/target/*.jar app.jar

EXPOSE 8005

ENTRYPOINT ["java","-jar","app.jar"]