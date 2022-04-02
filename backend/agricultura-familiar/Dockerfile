# syntax=docker/dockerfile:1
 
FROM maven:3.8.4-eclipse-temurin-16-alpine as build
WORKDIR /agricultura-familiar
COPY pom.xml .
RUN mvn dependency:go-offline    
COPY ./src ./src
RUN mvn clean install -Dmaven.test.skip=true

FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine-slim
WORKDIR /agricultura-familiar
COPY --from=build /agricultura-familiar/target/*.jar /agricultura-familiar/app.jar
ENTRYPOINT ["java", "-jar", "/agricultura-familiar/app.jar"]
