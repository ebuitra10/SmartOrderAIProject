FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /app

COPY . .

RUN ./mvnw clean package -pl msvc-products -am -DskipTests



FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/msvc-products/target/*.jar app.jar

EXPOSE 8003

ENTRYPOINT ["java","-jar","app.jar"]