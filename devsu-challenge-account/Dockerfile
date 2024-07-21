FROM maven:3.9.5-eclipse-temurin-17-alpine as builder
WORKDIR /app
ADD src ./src
COPY pom.xml .
RUN mvn clean package

FROM openjdk:17-oracle
COPY --from=builder /app/target/devsu-challenge-account.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "--add-opens", "java.base/java.util=ALL-UNNAMED", "-jar", "app.jar"]

LABEL maintainer="Fabian Perez"
LABEL email="cfpvasquez96@hotmail.com"