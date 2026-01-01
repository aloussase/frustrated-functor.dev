FROM gradle:8-jdk21 AS builder

WORKDIR /app

COPY . .

RUN ./gradlew build --no-daemon -x test

FROM eclipse-temurin:21

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
