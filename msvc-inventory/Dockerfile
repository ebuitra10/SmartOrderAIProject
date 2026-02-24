FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /app

COPY . .

RUN ./mvnw clean package -pl msvc-inventory -am -DskipTests



FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/msvc-inventory/target/*.jar app.jar

EXPOSE 8004

ENTRYPOINT ["java","-jar","app.jar"]