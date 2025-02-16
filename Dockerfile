FROM eclipse-temurin:21 AS builder
LABEL maintainer="rickyang2910@gmail.com"
WORKDIR /app 
COPY target/springboot-webservices-0.0.1-SNAPSHOT.jar /app/springboot-app.jar

ENTRYPOINT ["java", "-jar", "/app/springboot-app.jar"]