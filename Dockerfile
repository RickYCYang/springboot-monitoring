FROM eclipse-temurin:21 AS builder
LABEL maintainer="rickyang2910@gmail.com"
WORKDIR /app 

# copy Spring Boot app jar
COPY target/springboot-webservices-0.0.1-SNAPSHOT.jar app.jar

# copy Elastic APM agent jar
COPY target/apm/elastic-apm-agent-1.54.0.jar elastic-apm-agent.jar

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=docker \
  ELASTIC_APM_SERVICE_NAME=spring-app \
  ELASTIC_APM_SERVER_URL=http://apm-server:8200 \
  ELASTIC_APM_ENVIRONMENT=production

ENTRYPOINT ["java", "-javaagent:/app/elastic-apm-agent.jar", "-jar", "app.jar"]