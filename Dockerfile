FROM eclipse-temurin:21 AS builder
LABEL maintainer="rickyang2910@gmail.com"
WORKDIR /app 
COPY target/springboot-webservices-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=docker

ENTRYPOINT ["java", "-jar", "app.jar"]